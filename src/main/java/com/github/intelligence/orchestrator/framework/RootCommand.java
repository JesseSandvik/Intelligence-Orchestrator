package com.github.intelligence.orchestrator.framework;

import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

public class RootCommand {
    static int run(ParseResult pr) {
        Integer helpExitCode = CommandLine.executeHelpRequest(pr);
        return helpExitCode != null ? helpExitCode : pr.matchedOptionValue('c', 1);
    }
    public static void main(String[] args) {
        CommandSpec spec = CommandSpec.create()
                .name("io")
                .version("[IO] Version 1.0")
                .mixinStandardHelpOptions(true);

        spec.usageMessage()
                .headerHeading("Header heading%n")
                .header("header line 1", "header line 2")
                .descriptionHeading("Description heading%n")
                .description("description line 1", "description line 2")
                .optionListHeading("Options%n")
                .parameterListHeading("Positional Parameters%n")
                .footerHeading("Footer heading%n")
                .footer("footer line 1", "footer line 2");

        CommandLine cmd = new CommandLine(spec);

        cmd.setExecutionStrategy(RootCommand::run);
        int exitCode = cmd.execute(args);
        System.exit(exitCode);
    }
}