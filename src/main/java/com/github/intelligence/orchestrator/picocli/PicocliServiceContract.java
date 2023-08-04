package com.github.intelligence.orchestrator.picocli;

public interface PicocliServiceContract {
    void printUsage();
    void run();
    void run(String arg);
}
