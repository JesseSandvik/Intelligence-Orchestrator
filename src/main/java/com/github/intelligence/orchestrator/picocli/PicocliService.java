package com.github.intelligence.orchestrator.picocli;

public class PicocliService implements PicocliServiceContract {

    private static PicocliServiceUtility picoService;

    public PicocliService(String rootCmdName, String rootCmdVersion) {
        picoService = new PicocliServiceUtility(rootCmdName, rootCmdVersion);
    }

    @Override
    public void addSubcommand(String subCmdName) {
        picoService.addSubcommand(subCmdName);
    }

    @Override
    public void printUsage() {
        picoService.printUsage();
    }

    @Override
    public void run() {
        picoService.run();
    }

    @Override
    public void run(String arg) {
        picoService.run(arg);
    }
}
