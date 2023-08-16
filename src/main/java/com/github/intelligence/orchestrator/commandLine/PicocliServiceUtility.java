package com.github.intelligence.orchestrator.commandLine;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

import java.util.Objects;

class PicocliServiceUtility {
    private static CommandSpec rootSpec;

    public PicocliServiceUtility(String rootCommandName, String rootCommandVersion, String rootCommandDescription) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCommandName)
                .optionsCaseInsensitive(true)
                .subcommandsCaseInsensitive(true)
                .version(rootCommandVersion);

        setStandardizedUsageForCommandSpec(rootSpec, rootCommandDescription);
    }

    private CommandSpec setStandardOptions() {
        return CommandSpec.create()
                .addOption(OptionSpec.builder("-h", "--help")
                        .usageHelp(true)
                        .description("Show this help message and exit.").build())
                .addOption(OptionSpec.builder("-v", "--version")
                        .versionHelp(true)
                        .description("Print version information and exit.").build());
    }

    private void setStandardizedUsageForCommandSpec(CommandSpec commandSpec, String commandDescription) {
        commandSpec
                .addMixin("standardOptions", setStandardOptions())
                .usageMessage()
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

    public int run(String[] args) {
        CommandLine rootCommand = new CommandLine(rootSpec);
        rootCommand.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(rootCommand));

        if (args.length == 0 || args.length == 1 && Objects.equals(args[0].toLowerCase(), "help")) {
            rootCommand.usage(System.out);
            return 0;
        } else {
            return rootCommand.execute(args);
        }
    }
}
