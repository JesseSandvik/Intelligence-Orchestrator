package com.github.intelligence.orchestrator.picocli;

public interface PicocliServiceContract {
    void addSubcommand(String subCmdName, Runnable subCmd);
    void addParameterForSubcommand(String subCmdName, String paramLabel, Class<?> paramType, String paramDescription);
    void run();
    void run(String arg);
    void run(String[] args);
}
