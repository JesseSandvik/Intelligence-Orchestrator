package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.commandLine.CommandLineService;
import com.github.intelligence.orchestrator.properties.PropertiesService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PropertiesService rootCommandPropertiesService = new PropertiesService("/io.properties");
        String rootCommand = rootCommandPropertiesService.getProperty("app.command");
        String rootCommandVersion = rootCommandPropertiesService.getProperty("app.version");
        String applicationName = rootCommandPropertiesService.getProperty("app.name");
        String applicationDescription = rootCommandPropertiesService.getProperty("app.description");

        String formattedRootCommandVersion = rootCommand + " | " + applicationName + " | Version " + rootCommandVersion;

        CommandLineService commandLineService = new CommandLineService(
                rootCommand,
                formattedRootCommandVersion,
                applicationDescription
        );

        int exitCode = commandLineService.run(args);

        System.exit(exitCode);
    }
}