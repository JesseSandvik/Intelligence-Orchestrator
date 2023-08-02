package com.github.intelligence.orchestrator.framework;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

class CommandServiceUtility {
    private static CommandSpec rootSpec;

    public CommandServiceUtility(String rootCmdName) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCmdName);
    }

    public void enableStandardHelpOptions(boolean enableHelp) {
        rootSpec.mixinStandardHelpOptions(enableHelp);
    }

    public void printUsageMessage() {
        CommandLine cmd = new CommandLine(rootSpec);
        cmd.usage(System.out);
    }

    public void setDescription(String description) {
        rootSpec.usageMessage()
                .description(description);
    }

    public void setVersion(String rootCmdVersion) {
        rootSpec.version(rootCmdVersion);
    }

    public void run(String[] args) {
        int exitCode = new CommandLine(rootSpec).execute(args);
        System.exit(exitCode);
    }
}
