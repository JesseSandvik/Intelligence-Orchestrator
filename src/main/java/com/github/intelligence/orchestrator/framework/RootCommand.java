package com.github.intelligence.orchestrator.framework;

import com.command.Command;
import com.command.CommandFactory;
import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

import java.util.List;

public class RootCommand {
    static CommandSpec rootSpec;
    static List<Command> subcommands;
    static void setSpec() {
        rootSpec = CommandSpec.create()
                .name("io")
                .version("[IO] Version 1.0")
                .mixinStandardHelpOptions(true);
    }
    static void setSubcommands() {
        CommandFactory subcommandsFactory = new CommandFactory();
//        subcommandsFactory.addcommand();
        subcommands = subcommandsFactory.commands;
    }
    static void setUsageMessage() {
        rootSpec.usageMessage()
                .headerHeading("Header heading%n")
                .header("header line 1", "header line 2")
                .descriptionHeading("Description heading%n")
                .description("description line 1", "description line 2")
                .optionListHeading("Options%n")
                .parameterListHeading("Positional Parameters%n")
                .footerHeading("Footer heading%n")
                .footer("footer line 1", "footer line 2");
    }
    static int run(ParseResult pr) {
        Integer helpExitCode = CommandLine.executeHelpRequest(pr);
        return helpExitCode != null ? helpExitCode : pr.matchedOptionValue('c', 1);
    }
    public static void main(String[] args) {
        setSpec();
        setSubcommands();
        setUsageMessage();

        CommandLine cmd = new CommandLine(rootSpec);
        cmd.setExecutionStrategy(RootCommand::run);

        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }
}