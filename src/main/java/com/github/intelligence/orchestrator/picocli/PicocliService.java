package com.github.intelligence.orchestrator.picocli;

public class PicocliService implements PicocliServiceContract {
    private static PicocliServiceUtility picocliService;

    public PicocliService(String rootCommand, String rootCommandVersion, String rootCommandDescription) {
        picocliService = new PicocliServiceUtility(rootCommand, rootCommandVersion, rootCommandDescription);
    }

    @Override
    public void addSubcommand(String subcommandName, String subcommandVersion, String subcommandDescription, Runnable subcommandOperation) {
        picocliService.addSubcommand(subcommandName, subcommandVersion, subcommandDescription, subcommandOperation);
    }

    @Override
    public void addParameterForSubcommand(String subcommand, String parameterLabel, Class<?> parameterType, String parameterDescription) {
        picocliService.addParameterForSubcommand(subcommand, parameterLabel, parameterType, parameterDescription);
    }

    public void run(String[] args) {
        picocliService.run(args);
    }
}
