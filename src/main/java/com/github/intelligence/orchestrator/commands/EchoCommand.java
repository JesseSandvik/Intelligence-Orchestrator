package com.github.intelligence.orchestrator.commands;

public class EchoCommand implements Runnable, Command {
    @Override
    public void run() {
        System.out.println("HELLO WORLD!");
    }
}
