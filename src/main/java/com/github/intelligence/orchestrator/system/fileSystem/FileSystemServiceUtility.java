package com.github.intelligence.orchestrator.system.fileSystem;

import java.io.File;
import java.io.IOException;

public class FileSystemServiceUtility {
    public void createFile(String filePath) throws IOException {
        File fileToCreate = new File(filePath);

        if (!fileToCreate.exists()) {
            fileToCreate.createNewFile();
        }
    }

    public void deleteFile(String filePath) {
        File fileToDelete = new File(filePath);

        if (fileToDelete.exists()) {
            fileToDelete.delete();
        }
    }
}
