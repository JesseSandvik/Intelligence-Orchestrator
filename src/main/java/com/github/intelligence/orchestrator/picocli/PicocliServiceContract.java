package com.github.intelligence.orchestrator.picocli;

public interface PicocliServiceContract {
    void addSubcommand(String subcommandName, String subcommandDescription, String subcommandVersion, Runnable subcommand);
    void addParameterForSubcommand(String subCmdName, String paramLabel, Class<?> paramType, String paramDescription);
    void run();
    void run(String arg);
    void run(String[] args);
}
