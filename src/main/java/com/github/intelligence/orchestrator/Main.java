package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.command.Command;
import com.github.intelligence.orchestrator.services.command.CommandService;

public class Main {

    public static void main(String[] args) {
        Command command = new Command("io", "1.0.0", "Test description.");
        CommandService commandService = new CommandService(command);
        int exitCode = commandService.run();

        System.exit(exitCode);
    }
}