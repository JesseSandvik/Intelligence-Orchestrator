package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.system.fileSystem.FileSystemServiceContract;
import com.github.intelligence.orchestrator.system.fileSystem.FileSystemService;
import com.github.intelligence.orchestrator.system.operatingSystem.OperatingSystemService;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OperatingSystemService osService = new OperatingSystemService();
        FileSystemServiceContract fsService = new FileSystemService();
        String tempDir = fsService.createTempDirectory();
//        String tempFileA = tempPath + osService.getOperatingSystemFileSeparator() + "testa1234";
//        String tempFileB = tempPath + osService.getOperatingSystemFileSeparator() + "testb5678";
//        if (fileService.createFile(tempFileA)) {
//            System.out.println("CREATED FILE: " + tempFileA);
//        }
//
//        if (fileService.createFile(tempFileB)) {
//            System.out.println("CREATED FILE: " + tempFileB);
//        }
//
//        System.out.println(tempPath);
//        System.out.println(tempFileA);
//        System.out.println(tempFileB);

//        if (fileService.deleteDirectoryAndContent(tempPath)) {
//            System.out.println("DELETED DIRECTORY AMD ALL CONTENT: " + tempPath);
//        }
    }
}
