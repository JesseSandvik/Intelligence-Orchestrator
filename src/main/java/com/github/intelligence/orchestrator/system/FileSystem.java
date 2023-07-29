package com.github.intelligence.orchestrator.system;

import java.io.File;
import java.util.Objects;

public class FileSystem {
    private static File file;
    private static String fileName;
    public boolean fileExists(String fileNameArg) {
        if (file == null || !Objects.equals(fileNameArg, fileName)) {
            file = new File(fileNameArg);
            fileName = fileNameArg;
        }
        return file.exists();
    }
    public boolean fileCanExecute(String fileNameArg) {
        if (file == null || !Objects.equals(fileNameArg, fileName)) {
            file = new File(fileNameArg);
            fileName = fileNameArg;
        }
        return file.canExecute();
    }
}
