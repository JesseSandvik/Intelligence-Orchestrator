package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

class PicocliServiceUtility {
    private static CommandSpec rootSpec;

    public PicocliServiceUtility(String rootCmdName, String rootCmdVersion) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCmdName)
                .version(rootCmdVersion);

        setStandardizedUsageForCommandSpec(rootSpec);
    }

    public void addSubcommand(String subCmdName, String subCmdVersion, Runnable subCmd) {
        rootSpec.addSubcommand(subCmdName, CommandSpec.wrapWithoutInspection(subCmd));
        rootSpec.usageMessage().commandListHeading();

        CommandLine currentSubCmd = rootSpec.subcommands().get(subCmdName);
        CommandSpec subCmdSpec = currentSubCmd.getCommandSpec();

        subCmdSpec.version(subCmdVersion);
        setStandardizedUsageForCommandSpec(subCmdSpec);
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
                    String rootCmdName = rootCmd.getCommandName();

                    if (firstUnmatchedArgument.startsWith("-")) {
                        rootCmd.getErr().println("'" + firstUnmatchedArgument + "'" + " is not a recognized option for " + rootCmdName + ".");
                        rootCmd.getErr().println("Please refer to the 'Options' section for available options.\n");
                    } else {
                        rootCmd.getErr().println("'" + firstUnmatchedArgument + "'" + " is not a recognized parameter or command for " + rootCmdName + ".");
                        rootCmd.getErr().println("Please refer to the 'Commands' section for available commands.\n");
                    }
                    rootCmd.usage(rootCmd.getErr());
                    return rootCmd.getCommandSpec().exitCodeOnInvalidInput();
                }
                return defaultHandler.handleParseException(e, strings);
            }
        };
    }

    private void setStandardizedUsageForCommandSpec(CommandSpec cmdSpec) {
        cmdSpec.mixinStandardHelpOptions(true);

        cmdSpec.usageMessage()
                .commandListHeading("\nCommands:%n")
                .optionListHeading("\nOptions:%n")
                .parameterListHeading("\nParameters:%n");
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
