package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.picocli.PicocliService;
import com.github.intelligence.orchestrator.properties.PropertiesService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesService propertiesService = new PropertiesService("/root.properties");
        PicocliService cliService = new PicocliService(
                propertiesService.getProperty("app.command"),
                propertiesService.getProperty("app.name"),
                propertiesService.getProperty("app.description"),
                propertiesService.getProperty("app.version")
        );

        cliService.run(args);
    }
}