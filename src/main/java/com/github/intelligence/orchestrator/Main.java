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

//        String tempDir = fileService.createTempDirectoryWithPrefix("temp--");
        String tempPath = osService.getOperatingSystemTmpDir() + osService.getOperatingSystemFileSeparator() + "abcdir123";
        fileService.createDirectory(tempPath + osService.getOperatingSystemFileSeparator() + "nested1");
        String tempFileA = tempPath + osService.getOperatingSystemFileSeparator() + "testa1234";
        String tempFileB = tempPath + osService.getOperatingSystemFileSeparator() + "testb5678";
        fileService.createFile(tempFileA);
        fileService.createFile(tempFileB);
        System.out.println(tempPath);
        System.out.println(tempFileA);
        System.out.println(tempFileB);

        fileService.deleteDirectoryAndContent(tempPath);
    }
}
