package com.github.intelligence.orchestrator.picocli;

public interface PicocliServiceContract {
    void addSubcommand(String subcommand, String subcommandVersion, String subcommandDescription, Runnable subcommandOperation);
    void addParameterForSubcommand(String subcommand, String parameterLabel, Class<?> parameterType, String parameterDescription);
    void run(String[] args);
}
