package com.github.intelligence.orchestrator.framework;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

class CommandServiceUtility {
    private static CommandSpec rootSpec;

    public CommandServiceUtility(String rootCmdName) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCmdName)
                .mixinStandardHelpOptions(true);
    }

    public void printUsageMessage() {
        CommandLine cmd = new CommandLine(rootSpec);
        cmd.usage(System.out);
    }

    public void setUsageMessageHeader() {
        rootSpec.usageMessage()
                .abbreviateSynopsis(true);
    }
}
