package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.util.List;

public class FileSystemServiceImpl implements FileSystemService {
    private final FileSystemServiceUtility fileUtility;

    public FileSystemServiceImpl() {
        this.fileUtility = new FileSystemServiceUtility();
    }

    @Override
    public List<String> listFiles(String directoryPath) {
        return null;
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

    }

    @Override
    public void copyFile(String sourceFilePath, String destinationFilePath) throws IOException {

    }

    @Override
    public void renameFile(String filePath, String newFileName) throws IOException {

    }
}
