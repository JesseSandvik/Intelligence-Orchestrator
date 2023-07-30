package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.system.fileSystem.FileSystemService;
import com.github.intelligence.orchestrator.system.fileSystem.FileSystemServiceImpl;
import com.github.intelligence.orchestrator.system.operatingSystem.OperatingSystemServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OperatingSystemServiceImpl osService = new OperatingSystemServiceImpl();
        FileSystemService fileService = new FileSystemServiceImpl();

        System.out.println(fileService.listFiles(osService.getOperatingSystemUserDir()));

        String tempDir = fileService.createTempDirectoryWithPrefix("temp--");
        String tempFileA = tempDir + osService.getOperatingSystemFileSeparator() + "test1234";
        String tempFileB = tempDir + osService.getOperatingSystemFileSeparator() + "test5678";
        fileService.createFile(tempFileA);
        fileService.createFile(tempFileB);
        System.out.println(tempDir);
        System.out.println(tempFileA);
        System.out.println(tempFileB);

        fileService.deleteDirectoryAndContent(tempDir);
    }
}
