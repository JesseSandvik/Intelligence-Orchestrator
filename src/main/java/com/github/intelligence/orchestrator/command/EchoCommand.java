package com.github.intelligence.orchestrator.command;

public class EchoCommand implements Runnable {
    private final String argument;

    public EchoCommand(String argument) {
        this.argument = argument;
    }

    @Override
    public void run() {
        System.out.println(argument);
    }
}
