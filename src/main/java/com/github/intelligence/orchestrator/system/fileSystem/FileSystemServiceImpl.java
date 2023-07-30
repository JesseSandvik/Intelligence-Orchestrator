package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.util.List;

public class FileSystemServiceImpl implements FileSystemService {
    private final FileSystemServiceUtility fileUtility;

    public FileSystemServiceImpl() {
        this.fileUtility = new FileSystemServiceUtility();
    }

    @Override
    public boolean createTempDirectory() throws IOException {
        return fileUtility.createTempDirectory();
    }

    @Override
    public boolean createTempDirectory(String prefix) throws IOException {
        return fileUtility.createTempDirectory(prefix);
    }

    @Override
    public boolean createDirectory(String dirPath) throws IOException {
        return fileUtility.createDirectory(dirPath);
    }

    @Override
    public boolean createFile(String filePath) throws IOException {
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
    public void moveFile(String sourceFilePath, String destinationFilePath) throws IOException {
        fileUtility.moveFile(sourceFilePath, destinationFilePath);
    }

    @Override
    public void copyFile(String sourceFilePath, String destinationFilePath) throws IOException {
        fileUtility.copyFile(sourceFilePath, destinationFilePath);
    }

    @Override
    public void renameFile(String filePath, String newFileName) throws IOException {
        fileUtility.renameFile(filePath, newFileName);
    }
}