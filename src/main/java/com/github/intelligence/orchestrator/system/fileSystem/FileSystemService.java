package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.util.List;

public interface FileSystemService {
    List<String> listFiles(String directoryPath);
    void createFile(String filePath) throws IOException;
    void deleteFile(String filePath) throws IOException;
    void moveFile(String sourceFilePath, String destinationFilePath) throws IOException;
    void copyFile(String sourceFilePath, String destinationFilePath) throws IOException;
    void renameFile(String filePath, String newFileName) throws IOException;
}
