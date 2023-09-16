package com.github.intelligence.orchestrator.picocli;

import com.github.intelligence.orchestrator.command.Command;
import picocli.CommandLine;
import picocli.CommandLine.Model.*;

public class CommandLineBuilder {
    private final Command command;
    private final CommandSpec commandSpec;

    public CommandLineBuilder(Command command) {
        this.command = command;
        this.commandSpec = CommandSpec.create().name(command.getName());

        if (command.getVersion() != null) {
            this.commandSpec.version(command.getVersion());
        }

        if (command.getUsageDescriptionSynopsis() != null) {
            this.commandSpec.usageMessage().description(command.getUsageDescriptionSynopsis());
        }

        if (command.getUsageDescriptionFull() != null) {
            this.commandSpec.usageMessage().footer("\n" + command.getUsageDescriptionFull() + "%n");
        }

        this.commandSpec
                .usageMessage()
                .autoWidth(true)
                .adjustLineBreaksForWideCJKCharacters(true)
                .abbreviateSynopsis(true)
                .commandListHeading("\nCommands:%n")
                .optionListHeading("\nOptions:%n")
                .parameterListHeading("\nParameters:%n");
    }

    public CommandLineBuilder addHiddenHelpSubcommand() {
        CommandSpec helpSubcommandSpec = CommandSpec.forAnnotatedObject(new CommandLine.HelpCommand());
        helpSubcommandSpec.usageMessage().hidden(true);
        this.commandSpec.addSubcommand("help", helpSubcommandSpec);
        return this;
    }

    public CommandLineBuilder addStandardHelpOption() {
        this.commandSpec.addOption(OptionSpec
                .builder("-h", "--help")
                .usageHelp(true)
                .description("Show this help message and exit.")
                .build());
        return this;
    }

    public CommandLineBuilder addStandardVersionOption() {
        this.commandSpec.addOption(OptionSpec
                .builder("-v", "--version")
                .versionHelp(true)
                .description("Print version information and exit.")
                .build());
        return this;
    }

    public CommandLine build() {
        return new CommandLine(commandSpec);
    }
}
