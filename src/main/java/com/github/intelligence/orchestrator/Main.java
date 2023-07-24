package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.picocli.IOCommand;

public class Main implements Runnable {
    static IOCommand ioCommand;
    public static void main(String[] args) {
        ioCommand = new IOCommand(args);
    }
    @Override
    public void run() {
        System.exit(ioCommand.call());
    }
}