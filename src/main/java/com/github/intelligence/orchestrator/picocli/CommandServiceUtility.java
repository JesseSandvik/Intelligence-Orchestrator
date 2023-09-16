package com.github.intelligence.orchestrator.picocli;

import com.github.intelligence.orchestrator.command.Command;
import picocli.CommandLine;

public class CommandServiceUtility {
    private final Command rootCommand;

    public CommandServiceUtility(Command rootCommand) {
        this.rootCommand = rootCommand;
    }

    public Integer execute(String[] args) {
        CommandLine rootCommandLine = new CommandLineBuilder(rootCommand)
                .addStandardHelpOption()
                .addHiddenHelpSubcommand()
                .addStandardVersionOption()
                .build();

        return rootCommandLine.execute(args);
    }
}
