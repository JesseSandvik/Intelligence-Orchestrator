package com.github.intelligence.orchestrator.system.operatingSystem;

import java.io.IOException;

public interface OperatingSystemService {
    String getOperatingSystemTmpDir();
    String getOperatingSystemName();
    String getOperatingSystemVersion();
    String getOperatingSystemArchitecture();
    String getOperatingSystemFileSeparator();
    String getOperatingSystemPathSeparator();
    String getOperatingSystemLineSeparator();
    String getOperatingSystemUserName();
    String getOperatingSystemUserHome();
    String getOperatingSystemUserDir();
    void executeShellCommand(String command) throws IOException;
}
