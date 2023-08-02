package com.github.intelligence.orchestrator.framework;

import picocli.CommandLine;

public class CommandService implements CommandServiceContract {
    private final CommandServiceUtility cmdUtility;

    public CommandService(String rootCmdName) {
        cmdUtility = new CommandServiceUtility(rootCmdName);
    }

    @Override
    public CommandLine.Command createCommand(String commandName, String command) {
        return null;
    }

    @Override
    public void addSubcommand(CommandLine.Command parentCommand, CommandLine.Command subcommand) {

    }

    @Override
    public void executeCommand() {

    }
}
