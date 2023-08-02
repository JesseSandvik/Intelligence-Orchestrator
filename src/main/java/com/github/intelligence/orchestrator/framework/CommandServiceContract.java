package com.github.intelligence.orchestrator.framework;

import picocli.CommandLine.Command;

public interface CommandServiceContract {
    Command createCommand(String commandName, String command);
    void addSubcommand(Command parentCommand, Command subcommand);
    void executeCommand();
}
