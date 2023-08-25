package com.github.intelligence.orchestrator.command;

public class Command {
    private final String name;
    private final String version;
    private final String description;
    private final String[] args;

    public Command(String name, String version, String description, String[] args) {
        this.name = name;
        this.version = version;
        this.description = description;
        this.args = args;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getDescription() {
        return description;
    }

    public String[] getArgs() {
        return args;
    }
}
