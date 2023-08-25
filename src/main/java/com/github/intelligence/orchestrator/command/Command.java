package com.github.intelligence.orchestrator.command;

public class Command {
    private final String name;
    private final String version;
    private final String description;

    public Command(String name, String version, String description) {
        this.name = name;
        this.version = version;
        this.description = description;
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
}
