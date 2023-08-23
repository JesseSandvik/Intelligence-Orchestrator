package com.github.intelligence.orchestrator.commandLine;

public interface CommandLineServiceContract {
    void addSubcommand(
            String subcommandName,
            String subcommandVersion,
            String subcommandDescription,
            Runnable subcommandOperation
    );
    void addSubcommandParameter(
            String subcommandName,
            String parameterLabel,
            Class<?> parameterType,
            String parameterDescription
    );
    int run(String[] args);
}
