package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.command.Command;
import com.github.intelligence.orchestrator.service.CommandService;

public class Main {

    public static void main(String[] args) {
        Command ioCommand = new Command("io");
        ioCommand.setVersion("1.0.0");
        ioCommand.setUsageDescriptionSynopsis("Usage description synopsis.");
        ioCommand.setUsageDescriptionFull("Usage description full.");

        CommandService commandService = new CommandService(ioCommand);
        int exitCode = commandService.execute(args);

        System.exit(exitCode);
    }
}