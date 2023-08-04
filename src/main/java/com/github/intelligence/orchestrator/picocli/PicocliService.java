package com.github.intelligence.orchestrator.picocli;

public class PicocliService implements PicocliServiceContract {
    private static PicocliServiceUtility picocliService;

    public PicocliService(String rootCmdName, String rootCmdVersion) {
        picocliService = new PicocliServiceUtility(rootCmdName, rootCmdVersion);
    }

    public void run() {
        picocliService.run();
    }

    public void run(String arg) {
        picocliService.run(arg);
    }
}
