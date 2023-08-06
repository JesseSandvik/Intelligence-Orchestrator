package com.github.intelligence.orchestrator.picocli;

public class PicocliService implements PicocliServiceContract {
    private static PicocliServiceUtility picocliService;

    public PicocliService(String rootCmdName, String rootCmdVersion) {
        picocliService = new PicocliServiceUtility(rootCmdName, rootCmdVersion);
    }

    public void addSubcommand(String subCmdName) {
        picocliService.addSubcommand(subCmdName);
    }

    public void run() {
        picocliService.run();
    }

    public void run(String arg) {
        picocliService.run(arg);
    }

    public void run(String[] args) {
        picocliService.run(args);
    }
}
