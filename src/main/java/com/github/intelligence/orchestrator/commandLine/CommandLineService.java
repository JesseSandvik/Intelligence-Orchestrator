package com.github.intelligence.orchestrator.commandLine;

public class CommandLineService implements CommandLineServiceContract {
    private static PicocliServiceUtility commandLineService;

    public CommandLineService(String rootCommand, String rootCommandVersion, String rootCommandDescription) {
        commandLineService = new PicocliServiceUtility(rootCommand, rootCommandVersion, rootCommandDescription);
    }

    @Override
    public void addSubcommand(
            String subcommandName,
            String subcommandVersion,
            String subcommandDescription,
            Runnable subcommandOperation
    ) {
        commandLineService.addSubcommand(subcommandName, subcommandVersion, subcommandDescription, subcommandOperation);
    }

    public int run(String[] args) {
        return commandLineService.run(args);
    }
}
