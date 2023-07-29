package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.system.fileSystem.FileSystemService;
import com.github.intelligence.orchestrator.system.fileSystem.FileSystemServiceImpl;
import com.github.intelligence.orchestrator.system.operatingSystem.OperatingSystemServiceImpl;

public class Main {
    public static void main(String[] args) {
        OperatingSystemServiceImpl osService = new OperatingSystemServiceImpl();
        FileSystemService fileService = new FileSystemServiceImpl();

        System.out.println(osService.getOperatingSystemName());
        System.out.println(osService.getOperatingSystemVersion());
        System.out.println(osService.getOperatingSystemArchitecture());
        System.out.println(osService.getOperatingSystemFileSeparator());
        System.out.println(osService.getOperatingSystemPathSeparator());
        System.out.println(osService.getOperatingSystemLineSeparator());
        System.out.println(osService.getOperatingSystemUserName());
        System.out.println(osService.getOperatingSystemUserHome());
        System.out.println(osService.getOperatingSystemUserDir());

        System.out.println(fileService.listFiles(osService.getOperatingSystemUserDir()));
    }
}
