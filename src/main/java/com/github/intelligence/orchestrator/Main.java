package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.system.fileSystem.FileSystemService;
import com.github.intelligence.orchestrator.system.fileSystem.FileSystemServiceImpl;
import com.github.intelligence.orchestrator.system.operatingSystem.OperatingSystemServiceImpl;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        OperatingSystemServiceImpl osService = new OperatingSystemServiceImpl();
        FileSystemService fileService = new FileSystemServiceImpl();

        String tempPath = osService.getOperatingSystemTmpDir() + osService.getOperatingSystemFileSeparator() + "abcdir123";
        String nestedTempPath = tempPath + osService.getOperatingSystemFileSeparator() + "nested1";
        if (fileService.createTempDirectory()) {
            System.out.println("CREATED DIRECTORY: " + fileService.getTempDirectory());
        }
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
