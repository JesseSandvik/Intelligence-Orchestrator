package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class FileSystemServiceImpl implements FileSystemService {
    private final FileSystemServiceUtility fileUtility;

    public FileSystemServiceImpl() {
        this.fileUtility = new FileSystemServiceUtility();
    }

    @Override
    public Path createTempDirectoryWithPrefix(String prefix) {
        return fileUtility.createTempDirectoryWithPrefix(prefix);
    }

    @Override
    public List<String> listFiles(String directoryPath) {
        return fileUtility.listFiles(directoryPath);
    }

    @Override
    public boolean createFile(String filePath) {
        return fileUtility.createFile(filePath);
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
