package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.IOException;
import java.util.List;

public interface FileSystemService {
    boolean createTempDirectoryWithPrefix(String prefix);
    List<String> listFiles(String directoryPath);
    boolean createFile(String filePath) throws IOException;
    boolean deleteFile(String filePath) throws IOException;
    boolean moveFile(String sourceFilePath, String destinationFilePath) throws IOException;
    boolean copyFile(String sourceFilePath, String destinationFilePath) throws IOException;
    boolean renameFile(String filePath, String newFileName) throws IOException;
}
