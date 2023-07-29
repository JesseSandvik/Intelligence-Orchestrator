package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.util.List;

public class FileSystemServiceImpl implements FileSystemService {
    private final FileSystemServiceUtility fileUtility;

    public FileSystemServiceImpl() {
        this.fileUtility = new FileSystemServiceUtility();
    }

    @Override
    public boolean createTempDirectoryWithPrefix(String prefix) {
        return fileUtility.createTempDirectoryWithPrefix(prefix);
    }

    @Override
    public List<String> listFiles(String directoryPath) {
        return fileUtility.listFiles(directoryPath);
    }

    @Override
    public void createFile(String filePath) throws IOException {
        fileUtility.createFile(filePath);
    }

    @Override
    public void deleteFile(String filePath) throws IOException {
        fileUtility.deleteFile(filePath);
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
