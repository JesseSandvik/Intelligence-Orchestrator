package com.github.intelligence.orchestrator.system.fileSystem;

import java.util.List;

public class FileSystemServiceImpl implements FileSystemService {
    private final FileSystemServiceUtility fileUtility;

    public FileSystemServiceImpl() {
        this.fileUtility = new FileSystemServiceUtility();
    }

    @Override
    public boolean createTempDirectory() {
        return fileUtility.createTempDirectory();
    }

    @Override
    public boolean createTempDirectory(String prefix) {
        return fileUtility.createTempDirectory(prefix);
    }

    @Override
    public boolean createDirectory(String dirPath) {
        return fileUtility.createDirectory(dirPath);
    }

    @Override
    public boolean createFile(String filePath) {
        return fileUtility.createFile(filePath);
    }

    @Override
    public List<String> listFiles(String dirPath) {
        return fileUtility.listFiles(dirPath);
    }

    @Override
    public String getTempDirectory() {
        return fileUtility.getTempdir();
    }

    @Override
    public boolean deleteDirectoryWithContent(String dirPath) {
        return fileUtility.deleteDirectoryWithContent(dirPath);
    }

    @Override
    public boolean deleteFile(String filePath) {
        return fileUtility.deleteFile(filePath);
    }

    @Override
    public boolean moveFile(String sourceFilePath, String destinationFilePath) {
        return fileUtility.moveFile(sourceFilePath, destinationFilePath);
    }

    @Override
    public boolean copyFile(String sourceFilePath, String destinationFilePath) {
        return fileUtility.copyFile(sourceFilePath, destinationFilePath);
    }

    @Override
    public boolean renameFile(String filePath, String newFileName) {
        return fileUtility.renameFile(filePath, newFileName);
    }
}