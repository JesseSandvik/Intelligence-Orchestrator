package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.picocli.PicocliService;
import com.github.intelligence.orchestrator.properties.PropertiesService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesService propertiesService = new PropertiesService("/io.properties");
        PicocliService cliService = new PicocliService("io", "[IO] Version " + propertiesService.getProperty("version"));
        cliService.addSubcommand("echo");

        cliService.run();
    }
}
