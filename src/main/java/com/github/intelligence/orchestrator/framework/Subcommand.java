package com.github.intelligence.orchestrator.framework;

import com.command.Command;

import java.io.*;

public class Subcommand implements Command {
    private String name;
    private String filepath;
    public Subcommand(String name, String filepath) {
        this.name = name;
        this.filepath = filepath;
    }
    public String getName() {
        return name;
    }
    public String getFilepath() {
        return filepath;
    }
    @Override
    public boolean exists() {
//        return new File(filepath).exists();
        return true;
    }
    @Override
    public boolean executes() {
//        return new File(filepath).canExecute();
        return true;
    }
    @Override
    public void execute(String[] args) {
        try {
            final ProcessBuilder build = new ProcessBuilder();
            build.command(args);
            final Process process = build.start();

            final InputStream inputStream = process.getInputStream();
            final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                process.waitFor();
            }
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}
