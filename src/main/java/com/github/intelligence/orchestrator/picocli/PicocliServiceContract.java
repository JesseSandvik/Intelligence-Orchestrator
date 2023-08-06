package com.github.intelligence.orchestrator.picocli;

public interface PicocliServiceContract {
    void addSubcommand(String subCmdName, Runnable subCmd);
    void run();
    void run(String arg);
    void run(String[] args);
}
