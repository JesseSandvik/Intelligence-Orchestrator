package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

import static picocli.CommandLine.Model.CommandSpec.wrapWithoutInspection;

class PicocliServiceUtility {
    private static CommandSpec rootSpec;

    public PicocliServiceUtility(String rootCommand, String rootCommandVersion, String rootCommandDescription) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCommand)
                .version(rootCommandVersion);

        setStandardizedUsageForCommandSpec(rootSpec, rootCommandVersion, rootCommandDescription);
    }

    public void addSubcommand(String subcommand, String subcommandVersion, String subcommandDescription, Runnable subcommandOperation) {
        rootSpec.addSubcommand(subcommand, wrapWithoutInspection(subcommandOperation));

        CommandSpec subcommandSpec = rootSpec.subcommands().get(subcommand).getCommandSpec();
        subcommandSpec.version(subcommandVersion);

        setStandardizedUsageForCommandSpec(subcommandSpec, subcommandVersion, subcommandDescription);
    }

    public void addParameterForSubcommand(String subcommand, String parameterLabel, Class<?> parameterType, String parameterDescription) {
        CommandSpec subcommandSpec = rootSpec.subcommands().get(subcommand).getCommandSpec();

        subcommandSpec.addPositional(PositionalParamSpec.builder()
                .paramLabel(parameterLabel)
                .type(parameterType)
                .description(parameterDescription)
                .build());
    }

    private void setStandardizedUsageForCommandSpec(CommandSpec commandSpec, String commandVersion, String commandDescription) {
        commandSpec
                .mixinStandardHelpOptions(true)
                .usageMessage()
                .headerHeading(commandVersion + "%n\n")
                .abbreviateSynopsis(true)
                .autoWidth(true)
                .commandListHeading("\nCommands:%n")
                .optionListHeading("\nOptions:%n")
                .parameterListHeading("\nParameters:%n")
                .footer("\n" + commandDescription);
    }

    private IParameterExceptionHandler handleUnmatchedArgumentAtFirstIndex(CommandLine command) {
        return new IParameterExceptionHandler() {

            final IParameterExceptionHandler defaultHandler = command.getParameterExceptionHandler();
            @Override
            public int handleParseException(ParameterException exception, String[] strings) throws Exception {

                if (exception instanceof UnmatchedArgumentException) {
                    CommandLine currentCommand = exception.getCommandLine();
                    String firstUnmatchedArgument = ((UnmatchedArgumentException) exception).getUnmatched().get(0);
                    String currentCommandName = currentCommand.getCommandName();

                    if (firstUnmatchedArgument.startsWith("-")) {
                        String unknownOptionMessage = "'" + firstUnmatchedArgument + "'" + " is not a recognized option for " + currentCommandName + ".";

                        currentCommand.getErr().println(unknownOptionMessage);
                        currentCommand.getErr().println("Please refer to the 'Options' section for available options.\n");
                    } else {
                        String unmatchedParameterMessage = "'" + firstUnmatchedArgument + "'" + " is not a recognized parameter or command for " + currentCommandName + ".";

                        currentCommand.getErr().println(unmatchedParameterMessage);
                        currentCommand.getErr().println("Please refer to the 'Commands' section for available command.\n");
                    }
                    currentCommand.usage(currentCommand.getErr());
                    return currentCommand.getCommandSpec().exitCodeOnInvalidInput();
                }
                return defaultHandler.handleParseException(exception, strings);
            }
        };
    }

    private void printUsageForCommandSpec(CommandSpec commandSpec) {
        new CommandLine(commandSpec).usage(System.out);
    }

    private void handleSubcommandProvidedAsOnlyArgument(String[] args) {
        CommandLine subcommand = rootSpec.subcommands().get(args[0]);

        if (args.length == 1 && subcommand != null) {
            printUsageForCommandSpec(subcommand.getCommandSpec());
        }
    }

    public void run(String[] args) {
        if (args.length >= 1) {
            handleSubcommandProvidedAsOnlyArgument(args);

            CommandLine rootCommand = new CommandLine(rootSpec);
            rootCommand.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(rootCommand)).execute(args);
        } else {
            printUsageForCommandSpec(rootSpec);
        }
    }
}
