package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.framework.CommandService;
import com.github.intelligence.orchestrator.system.fileSystem.FileSystemService;
import com.github.intelligence.orchestrator.system.operatingSystem.OperatingSystemService;

import java.io.IOException;

public class Main {
    private final OperatingSystemService osService = new OperatingSystemService();
    private final FileSystemService fsService = new FileSystemService();
    private final CommandService cmdService = new CommandService("io");
    public static void main(String[] args) throws IOException {
    }
}
