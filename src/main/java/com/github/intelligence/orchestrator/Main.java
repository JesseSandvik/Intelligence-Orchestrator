package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.picocli.PicocliService;
import com.github.intelligence.orchestrator.properties.PropertiesService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesService rootCommandPropertiesService = new PropertiesService("/rootcommand.properties");
        String rootCommand = rootCommandPropertiesService.getProperty("app.command");
        String rootCommandVersion = rootCommandPropertiesService.getProperty("app.version");
        String applicationName = rootCommandPropertiesService.getProperty("app.name");
        String applicationDescription = rootCommandPropertiesService.getProperty("app.description");

        String formattedRootCommandVersionInformation = "[ " + rootCommand.toUpperCase() + " | " + applicationName + " | Version " + rootCommandVersion + " ]";
        PicocliService cliService = new PicocliService(
                rootCommand,
                formattedRootCommandVersionInformation,
                applicationDescription
        );

        cliService.run(args);
    }
}