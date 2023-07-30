package com.github.intelligence.orchestrator.system.fileSystem;

import java.util.List;

public interface FileSystemService {
    boolean createTempDirectory();
    boolean createTempDirectory(String prefix);
    boolean createDirectory(String dirPath);
    boolean createFile(String filePath);
    List<String> listFiles(String dirPath);
    String getTempDirectory();
    boolean deleteDirectoryWithContent(String dirPath);
    boolean deleteFile(String filePath);
    boolean moveFile(String sourceFilePath, String destinationFilePath);
    boolean copyFile(String sourceFilePath, String destinationFilePath);
    boolean renameFile(String filePath, String newFileName);
}
