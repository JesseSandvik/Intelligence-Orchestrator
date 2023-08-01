package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.util.List;

public class FileSystemService implements FileSystemServiceContract {
    private final FileSystemServiceUtility fsUtility;

    public FileSystemService() {
        this.fsUtility = new FileSystemServiceUtility();
    }

    @Override
    public String createTempDirectory() throws IOException {
        return fsUtility.createTempDirectory();
    }

    @Override
    public String createTempDirectory(String prefix) throws IOException {
        return fsUtility.createTempDirectory(prefix);
    }

    @Override
    public boolean createDirectory(String dirPath) throws IOException {
        return fsUtility.createDirectory(dirPath);
    }

    @Override
    public boolean createFile(String filePath) throws IOException {
        return fsUtility.createFile(filePath);
    }

    @Override
    public List<String> listFiles(String dirPath) {
        return fsUtility.listFiles(dirPath);
    }

    @Override
    public boolean deleteDirectoryWithContent(String dirPath) {
        return fsUtility.deleteDirectoryWithContent(dirPath);
    }

    @Override
    public boolean deleteFile(String filePath) {
        return fsUtility.deleteFile(filePath);
    }

    @Override
    public void moveFile(String sourceFilePath, String destinationFilePath) throws IOException {
        fsUtility.moveFile(sourceFilePath, destinationFilePath);
    }

    @Override
    public void copyFile(String sourceFilePath, String destinationFilePath) throws IOException {
        fsUtility.copyFile(sourceFilePath, destinationFilePath);
    }

    @Override
    public void renameFile(String filePath, String newFileName) throws IOException {
        fsUtility.renameFile(filePath, newFileName);
    }
}