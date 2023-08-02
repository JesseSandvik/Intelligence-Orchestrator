package com.github.intelligence.orchestrator.framework;

public interface CommandServiceContract {
    void enableStandardHelpOptions(boolean enableHelp);
    void printUsageMessage();
    void setVersion(String rootCmdVersion);
    void run(String[] args);
}
