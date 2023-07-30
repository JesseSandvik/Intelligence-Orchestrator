package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class FileSystemServiceUtility {
    private static String tempdir;

    public boolean createTempDirectory() {
        try {
            Path createdTempDir = Files.createTempDirectory("temp");

            if (createdTempDir != null) {
                createdTempDir.toFile().deleteOnExit();
                tempdir = createdTempDir.toString();
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean createTempDirectory(String prefix) {
        try {
            Path createdTempDir = Files.createTempDirectory(prefix);

            if (createdTempDir != null) {
                createdTempDir.toFile().deleteOnExit();
                tempdir = createdTempDir.toString();
                return true;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
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

    public String getTempdir() {
        return tempdir;
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
        }
        return false;
    }

    public boolean deleteDirectoryWithContent(String dirPath) {
        try {
            return deleteAllFilesAndDirectories(Paths.get(dirPath));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean deleteFile(String filePath) {
        try {
            File fileToDelete = new File(filePath);

            if (fileToDelete.exists()) {
                return fileToDelete.delete();
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean moveFile(String sourceFilePath, String destinationFilePath) {
        try {
            Files.move(Paths.get(sourceFilePath), Paths.get(destinationFilePath));
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean copyFile(String sourceFilePath, String destinationFilePath) {
        try {
            Files.copy(Paths.get(sourceFilePath), Paths.get(destinationFilePath));
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }

    public boolean renameFile(String filePath, String newFileName) {
        try {
            Path path = Paths.get(filePath);
            Path renamedPath = path.resolveSibling(newFileName);
            Files.move(path, renamedPath);
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return false;
    }
}
