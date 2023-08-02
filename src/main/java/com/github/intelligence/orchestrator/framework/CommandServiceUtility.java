package com.github.intelligence.orchestrator.framework;

import picocli.CommandLine.Model.*;

class CommandServiceUtility {
    private static CommandSpec rootSpec;

    public CommandServiceUtility(String rootCmdName) {
        rootSpec = CommandSpec.create();
        rootSpec.name(rootCmdName)
                .mixinStandardHelpOptions(true);
    }
}
