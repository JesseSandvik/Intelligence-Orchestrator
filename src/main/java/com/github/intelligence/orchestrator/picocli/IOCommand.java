package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine.Model.*;

public class IOCommand {
    CommandSpec spec;
    public IOCommand() {
        this.spec = CommandSpec.create();
    }
    public CommandSpec getSpec() {
        return spec;
    }
}
