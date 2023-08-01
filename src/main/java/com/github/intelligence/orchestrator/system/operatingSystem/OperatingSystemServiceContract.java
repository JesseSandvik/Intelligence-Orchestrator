package com.github.intelligence.orchestrator.system.operatingSystem;

import java.io.IOException;

public interface OperatingSystemServiceContract {
    String getOSTempDirectory();
    String getOSName();
    String getOSVersion();
    String getOSArchitecture();
    String getFileSeparator();
    String getPathSeparator();
    String getLineSeparator();
    String getOSUserName();
    String getOSUserHome();
    String getOSUserDirectory();
    void executeShellCommand(String command) throws IOException;
}
