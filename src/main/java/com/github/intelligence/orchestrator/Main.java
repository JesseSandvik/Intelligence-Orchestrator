package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.picocli.PicocliService;
import com.github.intelligence.orchestrator.properties.PropertiesService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesService rootCommandPropertiesService = new PropertiesService("/rootcommand.properties");
        PicocliService cliService = new PicocliService(
                rootCommandPropertiesService.getProperty("app.command"),
                rootCommandPropertiesService.getProperty("app.name"),
                rootCommandPropertiesService.getProperty("app.description"),
                rootCommandPropertiesService.getProperty("app.version")
        );

        PropertiesService subcommandPropertiesService = new PropertiesService("/subcommand.properties");
        cliService.addSubcommand(
                subcommandPropertiesService.getProperty("subcommand.command"),
                subcommandPropertiesService.getProperty("subcommand.description"),
                subcommandPropertiesService.getProperty("subcommand.version"),
                () -> {}
        );

        cliService.run(args);
    }
}