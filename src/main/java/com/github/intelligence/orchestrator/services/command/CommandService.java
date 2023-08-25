package com.github.intelligence.orchestrator.services.command;

import com.github.intelligence.orchestrator.command.Command;
import com.github.intelligence.orchestrator.services.command.picocli.PicocliCommandServiceUtility;

public class CommandService implements CommandServiceContract {
    private static PicocliCommandServiceUtility commandServiceUtility;

    public CommandService(Command command) {
        commandServiceUtility = new PicocliCommandServiceUtility(command);
    }

    @Override
    public int run() {
        return commandServiceUtility.run();
    }
}
