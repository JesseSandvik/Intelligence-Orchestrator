package com.github.intelligence.orchestrator.framework;

public class CommandService implements CommandServiceContract {
    private static CommandServiceUtility cmdUtility;

    public CommandService(String rootCmdName) {
        cmdUtility = new CommandServiceUtility(rootCmdName);
    }

    @Override
    public void enableStandardHelpOptions(boolean enableHelp) {
        cmdUtility.enableStandardHelpOptions(enableHelp);
    }

    @Override
    public void printUsageMessage() {
        cmdUtility.printUsageMessage();
    }

    @Override
    public void setVersion(String rootCmdVersion) {
        cmdUtility.setVersion(rootCmdVersion);
    }

    @Override
    public void run(String[] args) {
        if (args.length >= 1) {
            cmdUtility.run(args);
        } else {
            cmdUtility.printUsageMessage();
        }
    }
}
