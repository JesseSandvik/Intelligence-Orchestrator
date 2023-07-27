package com.github.intelligence.orchestrator.framework;

import com.command.Command;
import com.command.CommandFactory;
import com.command.InvalidCommandException;
import picocli.CommandLine;
import picocli.CommandLine.*;
import picocli.CommandLine.Model.*;

import java.io.*;
import java.util.Properties;

public class RootCommand {
    static CommandSpec rootSpec;
    static void setSpec() {
        rootSpec = CommandSpec.create()
                .name("io")
                .version("[IO] Version 1.0")
                .mixinStandardHelpOptions(true);
    }
    static void setSubcommands(String[] args) throws InvalidCommandException {
        CommandFactory subcommandsFactory = new CommandFactory();

        final String rootCommandExecutableDir = System.getProperty("user.dir");
        System.out.println("ROOT COMMAND EXECUTABLE DIR: " + rootCommandExecutableDir);
        final String rootDir = rootCommandExecutableDir.substring(0, rootCommandExecutableDir.lastIndexOf("\\"));
        System.out.println("ROOTDIR: " + rootDir);
        final String pluginsDir = String.format("%s/plugins", rootDir);

        final String subcommandPluginDir = String.format("%s/%s", pluginsDir, args[0]);
        final String subcommandExecutableFile = String.format("%s/%s", subcommandPluginDir, args[0]);

        Command subcommand = new Subcommand(args[0], subcommandExecutableFile);
        subcommandsFactory.addcommand(subcommand);

        rootSpec.addSubcommand(args[0], CommandSpec.wrapWithoutInspection((Runnable) () -> {
            CommandLine currentSubcommand = rootSpec.subcommands().get(args[0]);
            currentSubcommand.usage(System.out);
        }));
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
    public static void main(String[] args) throws InvalidCommandException {
        rootSpec = CommandSpec.create()
                .name("io")
                .mixinStandardHelpOptions(true);

        if (args.length < 1) {
            rootSpec.commandLine().usage(System.out);
            System.exit(0);
        }
        setSubcommands(args);

//        subcommand.execute(args);
        System.exit(new CommandLine(rootSpec).execute(args[0]));
    }
}