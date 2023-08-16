package com.github.intelligence.orchestrator.commandLine;

public class CommandLineService implements CommandLineServiceContract {
    private static PicocliServiceUtility commandLineService;

    public CommandLineService(String rootCommand, String rootCommandVersion, String rootCommandDescription) {
        commandLineService = new PicocliServiceUtility(rootCommand, rootCommandVersion, rootCommandDescription);
    }

    public int run(String[] args) {
        return commandLineService.run(args);
    }
}
