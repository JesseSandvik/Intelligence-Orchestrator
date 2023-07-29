package com.github.intelligence.orchestrator.system;

import java.io.File;

public class FileSystem {
    private File file;
    private void createFile(String filename) {
        this.file = new File(filename);
    }
    public boolean fileExists(String filename) {
        createFile(filename);
        return this.file.exists();
    }
    public boolean fileExecutes(String filename) {
        createFile(filename);
        return this.file.canExecute();
    }
}
