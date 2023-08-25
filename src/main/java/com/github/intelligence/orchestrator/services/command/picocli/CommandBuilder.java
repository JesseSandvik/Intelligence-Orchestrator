package com.github.intelligence.orchestrator.services.command.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

public class CommandBuilder {
    private final CommandSpec commandSpec;

    public CommandBuilder(String commandName, String commandVersion) {
        this.commandSpec = CommandSpec.create().name(commandName).version(commandVersion);
    }

    public CommandLine build() {
        return new CommandLine(commandSpec);
    }
}
