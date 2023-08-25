package com.github.intelligence.orchestrator.services.command.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

public class CommandBuilder {
    private final CommandSpec commandSpec;

    public CommandBuilder(String commandName, String commandVersion, String commandDescription) {
        this.commandSpec = CommandSpec.create().name(commandName).version(commandVersion);

        setStandardizedUsageForCommandSpec(this.commandSpec, commandDescription);
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

    public CommandLine build() {
        return new CommandLine(commandSpec);
    }
}
