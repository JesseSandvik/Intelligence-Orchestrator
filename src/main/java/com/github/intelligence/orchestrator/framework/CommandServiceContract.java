package com.github.intelligence.orchestrator.framework;

import picocli.CommandLine.Command;

public interface CommandServiceContract {
    void printUsageMessage();
    void setUsageMessageHeader();
}
