package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

class PicocliServiceUtility {
    private static CommandSpec rootSpec;

    public PicocliServiceUtility(String rootCmdName, String rootCmdVersion) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCmdName)
                .version(rootCmdVersion)
                .mixinStandardHelpOptions(true);
    }

    public void addSubcommand(String subCmdName, Runnable subCmd) {
        rootSpec.addSubcommand(subCmdName, CommandSpec.wrapWithoutInspection(subCmd));
    }

    private IParameterExceptionHandler handleUnmatchedArgumentAtFirstIndex(CommandLine cmd) {
        return new IParameterExceptionHandler() {

            final IParameterExceptionHandler defaultHandler = cmd.getParameterExceptionHandler();
            @Override
            public int handleParseException(ParameterException e, String[] strings) throws Exception {

                if (e instanceof UnmatchedArgumentException) {
                    CommandLine rootCmd = e.getCommandLine();
                    String firstUnmatchedArgument = ((UnmatchedArgumentException) e).getUnmatched().get(0);

                    if (!firstUnmatchedArgument.startsWith("-")) {
                        String rootCmdName = rootCmd.getCommandName();
                        rootCmd.getErr().println("'" + firstUnmatchedArgument + "'" + " is not a recognized parameter or command for " + rootCmdName + ".");
                        rootCmd.getErr().println("Please refer to the 'Commands' section for available commands.\n");
                        rootCmd.usage(rootCmd.getErr());
                        return rootCmd.getCommandSpec().exitCodeOnInvalidInput();
                    }
                }
                return defaultHandler.handleParseException(e, strings);
            }
        };
    }

    public void printUsage() {
        new CommandLine(rootSpec).usage(System.out);
    }

    public void run() {
        printUsage();
    }

    public void run(String arg) {
        CommandLine rootCmd = new CommandLine(rootSpec);

        rootCmd.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(rootCmd)).execute(arg);
    }

    public void run(String[] args) {
        CommandLine rootCmd = new CommandLine(rootSpec);

        rootCmd.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(rootCmd)).execute(args);
    }

}
