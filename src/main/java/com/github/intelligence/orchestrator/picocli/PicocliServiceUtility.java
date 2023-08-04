package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

class PicocliServiceUtility {
    private static CommandSpec rootSpec;

    public PicocliServiceUtility(String rootCmdName, String rootCmdVersion) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCmdName)
                .version(rootCmdVersion)
                .mixinStandardHelpOptions(true);
    }

    public void addSubcommand(String subCmdName) {}

    public void printUsage() {
        new CommandLine(rootSpec).usage(System.out);
    }

    public void run() {
        printUsage();
    }

    public void run(String arg) {
        new CommandLine(rootSpec).execute(arg);
    }
}
