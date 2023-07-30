package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface FileSystemService {
    String createTempDirectoryWithPrefix(String prefix);
    Boolean deleteDirectoryAndContent(String dirPath);
    List<String> listFiles(String dirPath);
    boolean createFile(String filePath) throws IOException;
    boolean deleteFile(String filePath) throws IOException;
    void moveFile(String sourceFilePath, String destinationFilePath) throws IOException;
    void copyFile(String sourceFilePath, String destinationFilePath) throws IOException;
    void renameFile(String filePath, String newFileName) throws IOException;
}
