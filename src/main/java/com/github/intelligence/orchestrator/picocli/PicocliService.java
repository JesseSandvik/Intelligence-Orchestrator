package com.github.intelligence.orchestrator.picocli;

public class PicocliService implements PicocliServiceContract {
    private static PicocliServiceUtility picocliService;

    public PicocliService(String rootCommand, String rootCommandName, String rootCommandDescription, String rootCommandVersion) {
        picocliService = new PicocliServiceUtility(rootCommand, rootCommandName, rootCommandDescription, rootCommandVersion);
    }

    public void addSubcommand(String subCmdName, String subCmdVersion, Runnable subCmd) {
        picocliService.addSubcommand(subCmdName, subCmdVersion, subCmd);
    }

    public void addParameterForSubcommand(String subCmdName, String paramLabel, Class<?> paramType, String paramDescription) {
        picocliService.addParameterForSubcommand(subCmdName, paramLabel, paramType, paramDescription);
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
