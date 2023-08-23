package com.github.intelligence.orchestrator.commandLine;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

import java.util.*;

import static picocli.CommandLine.Model.CommandSpec.wrapWithoutInspection;

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

    public void addSubcommand(
            String subcommandName,
            String subcommandVersion,
            String subcommandDescription,
            Runnable subcommandOperation
    ) {
        rootSpec.addSubcommand(
                subcommandName,
                wrapWithoutInspection(subcommandOperation)
        );

        CommandSpec subcommandSpec = rootSpec.subcommands().get(subcommandName).getCommandSpec();

        subcommandSpec
                .optionsCaseInsensitive(true)
                .subcommandsCaseInsensitive(true)
                .version(subcommandVersion);

        setStandardizedUsageForCommandSpec(subcommandSpec, subcommandDescription);
    }

    public void addSubcommandParameter(
            String subcommandName,
            String parameterLabel,
            Class<?> parameterType,
            String parameterDescription
    ) {
        CommandSpec subcommandSpec = rootSpec.subcommands().get(subcommandName).getCommandSpec();

        subcommandSpec.addPositional(PositionalParamSpec
                .builder()
                .paramLabel(parameterLabel)
                .type(parameterType)
                .description(parameterDescription)
                .build());
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

    private int getMatchingCharacterCount(char[] charListA, char[] charListB) {
        int matchingCharCount = 0;

        for (int i = 0; i < Math.min(charListA.length, charListB.length); i++) {
            if (charListA[i] == charListB[i]) {
                matchingCharCount += 1;
            }
        }
        return matchingCharCount;
    }

    private String getFormattedUnmatchedParameterAlertMessage(String unmatchedArgument, String parameterType, String commandName) {
        return "'" + unmatchedArgument + "'" + " is not a recognized " + parameterType.toLowerCase() + " for " + commandName + ".";
    }

    private IParameterExceptionHandler handleUnmatchedArgumentAtFirstIndex(CommandLine command) {
        return new IParameterExceptionHandler() {

            final IParameterExceptionHandler defaultHandler = command.getParameterExceptionHandler();
            @Override
            public int handleParseException(ParameterException exception, String[] strings) throws Exception {
                if (exception instanceof UnmatchedArgumentException) {
                    CommandLine currentCommand = exception.getCommandLine();
                    List<String> originalArguments = currentCommand.getParseResult().originalArgs();
                    String[] updatedArguments = new String[originalArguments.size()];

                    Map<String, String> unmatchedArguments = new LinkedHashMap<>();
                    for (String originalUnmatchedArgument : ((UnmatchedArgumentException) exception).getUnmatched()) {
                        if (originalUnmatchedArgument.startsWith("-")) {
                            unmatchedArguments.put(originalUnmatchedArgument, "Option");
                        } else {
                            unmatchedArguments.put(originalUnmatchedArgument, "Command");
                        }
                    }

                    List<String> availableParameters = new ArrayList<>(currentCommand.getSubcommands().keySet());
                    currentCommand.getCommandSpec().options().forEach(option -> availableParameters.add(option.longestName()));

                    Map<String, String> misspelledParameterCandidates = new LinkedHashMap<>();
                    for (String originalUnmatchedArgument : unmatchedArguments.keySet()) {
                        char[] unmatchedArgumentCharacters = originalUnmatchedArgument.replace("-", "").toLowerCase().toCharArray();

                        for (String availableParameter : availableParameters) {
                            int matchingCharacterCount = getMatchingCharacterCount(
                                    unmatchedArgumentCharacters,
                                    availableParameter.replace("-", "").toLowerCase().toCharArray()
                            );

                            if (matchingCharacterCount >= availableParameter.length() / 2) {
                                misspelledParameterCandidates.put(originalUnmatchedArgument, availableParameter);
                            }
                        }
                    }

                    if (!misspelledParameterCandidates.isEmpty()) {
                        for (Map.Entry<String, String> misspelledParameterCandidate : misspelledParameterCandidates.entrySet()) {
                            Scanner scan = new Scanner(System.in);
                            currentCommand.getErr().println(getFormattedUnmatchedParameterAlertMessage(misspelledParameterCandidate.getKey(), unmatchedArguments.get(misspelledParameterCandidate.getKey()), currentCommand.getCommandName()));
                            System.out.println("Did you mean " + "'" + misspelledParameterCandidate.getValue() + "'? [y/n]");
                            String userResponse = scan.nextLine();

                            if (userResponse.equalsIgnoreCase("y") || userResponse.equalsIgnoreCase("yes")) {

                                for (int i = 0; i < originalArguments.size(); i++) {
                                    if (originalArguments.get(i).equalsIgnoreCase(misspelledParameterCandidate.getKey())) {
                                        updatedArguments[i] = misspelledParameterCandidate.getValue();
                                    } else {
                                        updatedArguments[i] = originalArguments.get(i);
                                    }
                                }
                                return run(updatedArguments);
                            }
                        }
                    }

                    for (Map.Entry<String, String> unmatchedArgument : unmatchedArguments.entrySet()) {
                        currentCommand.getErr().println(getFormattedUnmatchedParameterAlertMessage(unmatchedArgument.getKey(), unmatchedArgument.getValue(), currentCommand.getCommandName()));
                        currentCommand.getErr().println("Please refer to the '" + unmatchedArgument.getValue() + "s' section for available "+ currentCommand.getCommandName() + " " + unmatchedArgument.getValue().toLowerCase() + "s.\n");
                    }

                    currentCommand.usage(currentCommand.getErr());
                    return currentCommand.getCommandSpec().exitCodeOnInvalidInput();
                }
                return defaultHandler.handleParseException(exception, strings);
            }
        };
    }

    private boolean providedArgumentForCommandIsHelp(CommandLine command, String arg) {
        return arg.equalsIgnoreCase("help");
    }

    private boolean providedArgumentForCommandIsSubcommand(CommandLine command, String arg) {
        CommandLine subcommand = command.getSubcommands().get(arg);
        return subcommand != null;
    }

    public int run(String[] args) {
        CommandLine rootCommand = new CommandLine(rootSpec);
        rootCommand.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(rootCommand));

        switch(args.length) {
            case 0:
                rootCommand.usage(System.out);
                return 0;
            case 1:
                if (providedArgumentForCommandIsHelp(rootCommand, args[0])) {
                    rootCommand.usage(System.out);
                    return 0;
                } else if (providedArgumentForCommandIsSubcommand(rootCommand, args[0])) {
                    rootCommand.getSubcommands().get(args[0]).usage(System.out);
                    return 0;
                }
            case 2:
                if (providedArgumentForCommandIsSubcommand(rootCommand, args[0]) && providedArgumentForCommandIsHelp(rootCommand, args[1])) {
                    rootCommand.getSubcommands().get(args[0]).usage(System.out);
                    return 0;
                }
            default:
                return rootCommand.execute(args);
        }
    }
}
