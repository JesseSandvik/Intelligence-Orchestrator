package com.github.intelligence.orchestrator.picocli;

import picocli.CommandLine;
import picocli.CommandLine.Model.*;

import java.util.concurrent.Callable;

public class IOCommand implements Callable<Integer> {
    CommandSpec spec;
    String[] args;
    public IOCommand(String[] args) {
        this.spec = CommandSpec.create();
        this.args = args;
    }
    @Override
    public Integer call() {
        this.spec.mixinStandardHelpOptions(true);
         return new CommandLine(this.spec).execute(this.args);
    }
}
