package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class FileSystemServiceUtility {
    public List<String> listFiles(String directoryPath) {
        File directory = new File(directoryPath);
        List<String> fileNames = new ArrayList<>();

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    fileNames.add(file.getName());
                }
            }
        }
        return fileNames;
    }
    public void createFile(String filePath) throws IOException {
        File fileToCreate = new File(filePath);

        if (!fileToCreate.exists()) {
            fileToCreate.createNewFile();
        }
    }

    public void deleteFile(String filePath) {
        File fileToDelete = new File(filePath);

        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }

    public void moveFile(String sourceFilePath, String destinationFilePath) throws IOException {
        Files.move(Paths.get(sourceFilePath), Paths.get(destinationFilePath));
    }

    public void copyFile(String sourceFilePath, String destinationFilePath) throws IOException {
        Files.copy(Paths.get(sourceFilePath), Paths.get(destinationFilePath));
    }

    public void renameFile(String filePath, String newFileName) throws IOException {
        Path path = Paths.get(filePath);
        Path renamedPath = path.resolveSibling(newFileName);
        Files.move(path, renamedPath);
    }
}
