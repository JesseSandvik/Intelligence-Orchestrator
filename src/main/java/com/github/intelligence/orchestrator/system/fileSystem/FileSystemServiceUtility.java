package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class FileSystemServiceUtility {

    public String createTempDirectory() throws IOException {
        Path createdTempDir = Files.createTempDirectory("temp");
        createdTempDir.toFile().deleteOnExit();
        return createdTempDir.toString();
    }

    public String createTempDirectory(String prefix) throws IOException {
        Path createdTempDir = Files.createTempDirectory(prefix);
        createdTempDir.toFile().deleteOnExit();
        return createdTempDir.toString();
    }

    public boolean createDirectory(String dirPath) throws IOException {
        File dirToCreate = new File(dirPath);

        if (!dirToCreate.exists()) {
            Files.createDirectories(dirToCreate.toPath());
            return true;
        }
        return false;
    }

    public boolean createFile(String filePath) throws IOException {
        File fileToCreate = new File(filePath);

        if (!fileToCreate.exists()) {
            return fileToCreate.createNewFile();
        }
        return false;
    }

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

    private boolean deleteAllFilesAndDirectories(Path parentDir) {
        File dirToDelete = parentDir.toFile();
        File[] directoryContent = dirToDelete.listFiles();

        if (directoryContent != null) {
            for (File content : directoryContent) {
                deleteAllFilesAndDirectories(content.toPath());
            }
        }
        return dirToDelete.delete();
    }

    public boolean deleteDirectoryWithContent(String dirPath) {
        return deleteAllFilesAndDirectories(Paths.get(dirPath));
    }

    public boolean deleteFile(String filePath) {
        File fileToDelete = new File(filePath);

        if (fileToDelete.exists()) {
            return fileToDelete.delete();
        }
        return false;
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
