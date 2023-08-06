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
                .usageMessage()
                .commandListHeading("\nCommands:%n")
                .optionListHeading("\nOptions:%n")
                .parameterListHeading("\nPositional Parameters:%n");
        rootSpec.mixinStandardHelpOptions(true);
    }

    public void addSubcommand(String subCmdName, Runnable subCmd) {
        rootSpec.addSubcommand(subCmdName, CommandSpec.wrapWithoutInspection(subCmd));
        rootSpec.usageMessage().commandListHeading();

        CommandLine currentSubCmd = rootSpec.subcommands().get(subCmdName);
        CommandSpec subCmdSpec = currentSubCmd.getCommandSpec();
        subCmdSpec.usageMessage()
                .customSynopsis(rootSpec.name() + " " + subCmdName)
                .optionListHeading("\nOptions:%n")
                .parameterListHeading("\nPositional Parameters:%n");
        subCmdSpec.mixinStandardHelpOptions(true);
    }

    public void addParameterForSubcommand(String subCmdName, String paramLabel, Class<?> paramType, String paramDescription) {
        CommandLine subCmd = rootSpec.subcommands().get(subCmdName);
        CommandSpec subCmdSpec = subCmd.getCommandSpec();

        subCmdSpec.addPositional(PositionalParamSpec.builder()
                .paramLabel(paramLabel)
                .type(paramType)
                .description(paramDescription).build());
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
        if (args.length == 0) {
            printUsage();
        } else {
            CommandLine rootCmd = new CommandLine(rootSpec);

            rootCmd.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(rootCmd)).execute(args);
        }
    }

}
