package com.github.intelligence.orchestrator.picocli;

public class PicocliService implements PicocliServiceContract {
    private static PicocliServiceUtility picocliService;

    public PicocliService(String rootCommandName, String applicationName, String rootCommandDescription, String rootCommandVersion) {
        picocliService = new PicocliServiceUtility(rootCommandName, applicationName, rootCommandDescription, rootCommandVersion);
    }

    public void addSubcommand(String subcommandName, String subcommandDescription, String subcommandVersion, Runnable subcommand) {
        picocliService.addSubcommand(subcommandName, subcommandDescription, subcommandVersion, subcommand);
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
