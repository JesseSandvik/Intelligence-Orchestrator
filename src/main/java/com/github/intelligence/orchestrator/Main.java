package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.commands.EchoCommand;
import com.github.intelligence.orchestrator.picocli.PicocliService;
import com.github.intelligence.orchestrator.properties.PropertiesService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        PropertiesService propertiesService = new PropertiesService("/io.properties");
        PicocliService cliService = new PicocliService("io", "[IO] Version " + propertiesService.getProperty("io.version"));
        cliService.addSubcommand("echo", "Echo Version " + propertiesService.getProperty("echo.version"), new EchoCommand());
        cliService.addParameterForSubcommand("echo", "message", String.class, "The message to be output from echo.");

        cliService.run("--badoption");
    }
}