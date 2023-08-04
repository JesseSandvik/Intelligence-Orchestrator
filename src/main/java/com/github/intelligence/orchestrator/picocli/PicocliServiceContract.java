package com.github.intelligence.orchestrator.picocli;

public interface PicocliServiceContract {
    void addSubcommand(String subCmdName);
    void printUsage();
    void run();
    void run(String arg);
}
