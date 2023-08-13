package com.github.intelligence.orchestrator.picocli;

public interface PicocliServiceContract {
    void addSubcommand(String subcommand, String subcommandVersion, String subcommandDescription, Runnable subcommandOperation);
    void run(String[] args);
}
