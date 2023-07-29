package com.github.intelligence.orchestrator.system;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileSystem {
    private static File file;
    private static String fileName;

    public void createFile(String fileNameArg) throws IOException {
        File fileToCreate = new File(fileNameArg);

        if (fileToCreate.createNewFile()) {
            System.out.println("File created: " + fileNameArg);
        } else {
            System.out.println("File already exists: " + fileNameArg);
        }
    }

    public void deleteFileOrEmptyDirectory(String path) {
        File fileToDelete = new File(path);

        if (fileToDelete.delete()) {
            System.out.println("File/Directory deleted: " + path);
        } else {
            System.out.println("File does not exist or is not an empty directory.");
        }
    }

    public boolean fileExists(String fileNameArg) {
        if (file == null || !Objects.equals(fileNameArg, fileName)) {
            file = new File(fileNameArg);
            fileName = fileNameArg;
        }
        return file.exists();
    }

    public boolean fileExistsAndExecutes(String fileNameArg) {
        if (file == null || !Objects.equals(fileNameArg, fileName)) {
            file = new File(fileNameArg);
            fileName = fileNameArg;
        }
        return file.canExecute();
    }
}
