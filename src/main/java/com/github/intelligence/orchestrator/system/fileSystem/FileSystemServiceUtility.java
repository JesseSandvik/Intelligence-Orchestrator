package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class FileSystemServiceUtility {
    public String createTempDirectoryWithPrefix(String prefix) {
        try {
            Path createdTempDir = Files.createTempDirectory(prefix);

            if (createdTempDir != null) {
                createdTempDir.toFile().deleteOnExit();
                return createdTempDir.toString();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return null;
    }

    private boolean deleteAllFilesAndDirectories(Path parentDir) {
        try {
            File dirToDelete = parentDir.toFile();
            File[] directoryContent = dirToDelete.listFiles();

            if (directoryContent != null) {
                for (File content : directoryContent) {
                    deleteAllFilesAndDirectories(content.toPath());
                }
            }
            return dirToDelete.delete();
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean deleteDirectoryAndContent(String dir) {
        try {
            deleteAllFilesAndDirectories(Paths.get(dir));
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
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

    public boolean createFile(String filePath) {
        try {
            File fileToCreate = new File(filePath);

            if (!fileToCreate.exists()) {
                return fileToCreate.createNewFile();
            }
            return false;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
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

    public boolean createDirectory(String dirPath) {
        try {
            File dirToCreate = new File(dirPath);

            if (!dirToCreate.exists()) {
                Files.createDirectories(dirToCreate.toPath());
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
