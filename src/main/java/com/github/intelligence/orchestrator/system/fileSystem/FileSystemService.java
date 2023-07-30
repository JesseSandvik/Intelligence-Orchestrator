package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.util.List;

public interface FileSystemService {
    boolean createTempDirectory() throws IOException;
    boolean createTempDirectory(String prefix) throws IOException;
    boolean createDirectory(String dirPath) throws IOException;
    boolean createFile(String filePath) throws IOException;
    List<String> listFiles(String dirPath);
    String getTempDirectory();
    boolean deleteDirectoryWithContent(String dirPath);
    boolean deleteFile(String filePath);
    void moveFile(String sourceFilePath, String destinationFilePath) throws IOException;
    void copyFile(String sourceFilePath, String destinationFilePath) throws IOException;
    void renameFile(String filePath, String newFileName) throws IOException;
}
