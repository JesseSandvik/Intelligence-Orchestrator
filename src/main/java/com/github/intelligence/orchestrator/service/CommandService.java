package com.github.intelligence.orchestrator.service;

import com.github.intelligence.orchestrator.command.Command;
import com.github.intelligence.orchestrator.contract.CommandServiceContract;
import com.github.intelligence.orchestrator.picocli.CommandServiceUtility;

public class CommandService implements CommandServiceContract {
    private final CommandServiceUtility commandServiceUtility;

    public CommandService(Command command) {
        this.commandServiceUtility = new CommandServiceUtility(command);
    }

    @Override
    public Integer execute(String[] args) {
        return this.commandServiceUtility.execute(args);
    }
}
