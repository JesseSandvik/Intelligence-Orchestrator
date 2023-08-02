package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.framework.CommandService;

public class Main {
        private static final CommandService cmdService = new CommandService("io");
    public static void main(String[] args) {
        cmdService.enableStandardHelpOptions(true);
        cmdService.setVersion("[IO] version 1.0");
//        cmdService.printUsageMessage();
        cmdService.run(args);
    }
}
