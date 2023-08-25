package com.github.intelligence.orchestrator.services.command;

import com.github.intelligence.orchestrator.services.command.picocli.PicocliCommandServiceUtility;

public class CommandService implements CommandServiceContract {
    private static PicocliCommandServiceUtility commandServiceUtility;

    public CommandService(String rootCommand, String rootCommandVersion, String rootCommandDescription) {
        commandServiceUtility = new PicocliCommandServiceUtility(rootCommand, rootCommandVersion, rootCommandDescription);
    }
}
