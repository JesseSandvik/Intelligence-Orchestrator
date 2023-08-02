package com.github.intelligence.orchestrator.framework;

public class CommandService implements CommandServiceContract {
    private final CommandServiceUtility cmdUtility;

    public CommandService(String rootCmdName) {
        cmdUtility = new CommandServiceUtility(rootCmdName);
    }

    @Override
    public void printUsageMessage() {
        cmdUtility.printUsageMessage();
    }

    @Override
    public void setUsageMessageHeader() {
        cmdUtility.setUsageMessageHeader();
    }
}
