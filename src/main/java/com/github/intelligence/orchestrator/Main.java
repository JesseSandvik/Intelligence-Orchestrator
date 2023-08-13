package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.command.EchoCommand;
import com.github.intelligence.orchestrator.picocli.PicocliService;
import com.github.intelligence.orchestrator.properties.PropertiesService;

import java.io.IOException;
import java.util.Objects;

public class Main {

    private static String getFormattedVersion(String versionPrefix, String version) {
        return "[ " + versionPrefix + " | Version " + version + " ]";
    }

    public static void main(String[] args) throws IOException {
        PropertiesService rootCommandPropertiesService = new PropertiesService("/io.properties");
        String rootCommand = rootCommandPropertiesService.getProperty("app.command");
        String rootCommandVersion = rootCommandPropertiesService.getProperty("app.version");
        String applicationName = rootCommandPropertiesService.getProperty("app.name");
        String applicationDescription = rootCommandPropertiesService.getProperty("app.description");

        String formattedRootCommandVersion = getFormattedVersion(rootCommand.toUpperCase() + " | " + applicationName, rootCommandVersion);

        PicocliService cliService = new PicocliService(
                rootCommand,
                formattedRootCommandVersion,
                applicationDescription
        );

        PropertiesService subcommandPropertiesService = new PropertiesService("/echo.properties");
        String subcommand = subcommandPropertiesService.getProperty("subcommand.command");
        String subcommandVersion = subcommandPropertiesService.getProperty("subcommand.version");
        String subcommandDescription = subcommandPropertiesService.getProperty("subcommand.description");

        String formattedSubcommandVersion = getFormattedVersion(subcommand.toUpperCase(), subcommandVersion);

        String echoCommandArgument = args.length == 2 && Objects.equals(args[0], "echo") ? args[1] : "";

        cliService.addSubcommand(
                subcommand,
                formattedSubcommandVersion,
                subcommandDescription,
                new EchoCommand(echoCommandArgument)
        );
        String formattedSubcommandFirstParameter = "[TEXT]";
        cliService.addParameterForSubcommand(subcommand, formattedSubcommandFirstParameter, String.class, "The text to be displayed as an output.");

        cliService.run(args);
    }
}