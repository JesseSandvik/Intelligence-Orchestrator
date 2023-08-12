package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

class PicocliServiceUtility {
    private static CommandSpec rootSpec;

    public PicocliServiceUtility(String rootCommandName, String applicationName, String rootCommandDescription, String rootCommandVersion) {
        String formattedVersionHeader = "[ " + rootCommandName.toUpperCase() + " | " + applicationName + " | Version " + rootCommandVersion + " ]";

        rootSpec = CommandSpec.create();
        rootSpec.name(rootCommandName)
                .version(formattedVersionHeader);

        rootSpec.usageMessage().headerHeading(formattedVersionHeader + "%n\n");
        setStandardizedUsageForCommandSpec(rootSpec, rootCommandDescription);
    }

    public void addSubcommand(String subcommandName, String subcommandDescription, String subcommandVersion , Runnable subcommand) {
        rootSpec.addSubcommand(subcommandName, CommandSpec.wrapWithoutInspection(subcommand));
        rootSpec.usageMessage().commandListHeading();

        CommandSpec currentSubcommandSpec = rootSpec.subcommands().get(subcommandName).getCommandSpec();

        currentSubcommandSpec.version(subcommandVersion);
        setStandardizedUsageForCommandSpec(currentSubcommandSpec, subcommandDescription);
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

    private void setStandardizedUsageForCommandSpec(CommandSpec commandSpec, String commandDescription) {
        commandSpec.mixinStandardHelpOptions(true);

        commandSpec.usageMessage()
                .abbreviateSynopsis(true)
                .autoWidth(true)
                .commandListHeading("\nCommands:%n")
                .optionListHeading("\nOptions:%n")
                .parameterListHeading("\nParameters:%n")
                .footer("\n" + commandDescription);
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
