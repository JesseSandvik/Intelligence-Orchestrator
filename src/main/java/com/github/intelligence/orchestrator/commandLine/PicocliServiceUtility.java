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

        rootSpec.addSubcommand("echo", wrapWithoutInspection((Runnable) () -> {}));

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

    private int getMatchingCharacterCount(char[] charListA, char[] charListB) {
        int matchingCharCount = 0;

        for (int i = 0; i < Math.min(charListA.length, charListB.length); i++) {
            if (charListA[i] == charListB[i]) {
                matchingCharCount += 1;
            }
        }
        return matchingCharCount;
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
                    for (String unmatchedArgument : ((UnmatchedArgumentException) exception).getUnmatched()) {
                        if (unmatchedArgument.startsWith("-")) {
                            unmatchedArguments.put(unmatchedArgument, "Option");
                        } else {
                            unmatchedArguments.put(unmatchedArgument, "Command");
                        }
                    }

                    List<String> availableSubcommandNames = new ArrayList<>(currentCommand.getSubcommands().keySet());
                    List<String> availableOptionLongestNames = new ArrayList<>();
                    currentCommand.getCommandSpec().options().forEach(option -> availableOptionLongestNames.add(option.longestName()));


                    Map<String, String> misspelledParameterCandidates = new LinkedHashMap<>();
                    for (String unmatchedArgument : unmatchedArguments.keySet()) {
                        char[] unmatchedArgumentCharacters = unmatchedArgument.replace("-", "").toLowerCase().toCharArray();

                        for (String subcommandName : availableSubcommandNames) {
                            int matchingCharacterCount = getMatchingCharacterCount(
                                    unmatchedArgumentCharacters,
                                    subcommandName.replace("-", "").toLowerCase().toCharArray()
                            );

                            if (matchingCharacterCount >= subcommandName.length() / 2) {
                                misspelledParameterCandidates.put(unmatchedArgument, subcommandName);
                            }
                        }

                        for (String optionLongestName : availableOptionLongestNames) {
                            int matchingCharacterCount = getMatchingCharacterCount(
                                    unmatchedArgumentCharacters,
                                    optionLongestName.replace("-", "").toLowerCase().toCharArray()
                            );

                            if (matchingCharacterCount >= optionLongestName.length() / 2) {
                                misspelledParameterCandidates.put(unmatchedArgument, optionLongestName);
                            }
                        }
                    }

                    if (!misspelledParameterCandidates.isEmpty()) {
                        for (Map.Entry<String, String> misspelledParameterCandidate : misspelledParameterCandidates.entrySet()) {
                            Scanner scan = new Scanner(System.in);
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
                                return currentCommand.setParameterExceptionHandler(handleUnmatchedArgumentAtFirstIndex(currentCommand)).execute(updatedArguments);
                            }
                        }
                    }

                    for (Map.Entry<String, String> unmatchedArgument : unmatchedArguments.entrySet()) {
                        currentCommand.getErr().println("'" + unmatchedArgument.getKey() + "'" + " is not a recognized " + unmatchedArgument.getValue().toLowerCase() + " for " + currentCommand.getCommandName() + ".");
                        currentCommand.getErr().println("Please refer to the '" + unmatchedArgument.getValue() + "s' section for available "+ currentCommand.getCommandName() + " " + unmatchedArgument.getValue().toLowerCase() + "s.\n");
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
