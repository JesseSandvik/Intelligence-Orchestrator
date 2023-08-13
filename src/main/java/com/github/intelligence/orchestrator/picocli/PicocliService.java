package com.github.intelligence.orchestrator.picocli;

public class PicocliService implements PicocliServiceContract {
    private static PicocliServiceUtility picocliService;

    public PicocliService(String rootCommand, String rootCommandVersion, String rootCommandDescription) {
        picocliService = new PicocliServiceUtility(rootCommand, rootCommandVersion, rootCommandDescription);
    }

    public void run(String[] args) {
        picocliService.run(args);
    }
}
