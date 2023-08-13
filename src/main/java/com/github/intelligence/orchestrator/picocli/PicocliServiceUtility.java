package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

class PicocliServiceUtility {
    private static CommandSpec rootSpec;

    public PicocliServiceUtility(String rootCommand, String rootCommandVersion, String rootCommandDescription) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCommand)
                .version(rootCommandVersion);

        setStandardizedUsageForCommandSpec(rootSpec, rootCommandVersion, rootCommandDescription);
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
                        currentCommand.getErr().println("'" + firstUnmatchedArgument + "'" + " is not a recognized option for " + currentCommandName + ".");
                        currentCommand.getErr().println("Please refer to the 'Options' section for available options.\n");
                    } else {
                        currentCommand.getErr().println("'" + firstUnmatchedArgument + "'" + " is not a recognized parameter or command for " + currentCommandName + ".");
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

    public void run(String[] args) {
        if (args.length == 0) {
            printUsageForCommandSpec(rootSpec);
        } else {
            CommandLine rootCommand = new CommandLine(rootSpec);
            rootCommand.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(rootCommand)).execute(args);
        }
    }

}
