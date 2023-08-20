package com.github.intelligence.orchestrator.commandLine;

public interface CommandLineServiceContract {
    void addSubcommand(
            String subcommandName,
            String subcommandVersion,
            String subcommandDescription,
            Runnable subcommandOperation
    );
    int run(String[] args);
}
