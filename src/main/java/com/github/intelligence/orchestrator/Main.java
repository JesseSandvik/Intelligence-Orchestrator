package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.framework.CommandService;

public class Main {
        static final CommandService cmdService = new CommandService("io");
    public static void main(String[] args) {
        cmdService.setUsageMessageHeader();
        cmdService.printUsageMessage();
    }
}
