package com.github.intelligence.orchestrator.system.operatingSystem;

import java.io.IOException;

public class OperatingSystemServiceUtility {
    private static String osName;
    private static String osVersion;
    private static String osArch;
    private static String fileSeparator;
    private static String pathSeparator;
    private static String lineSeparator;
    private static String userName;
    private static String userHome;
    private static String userDir;

    public String getOSName() {
        if (osName == null) {
            osName = System.getProperty("os.name");
        }
        return osName;
    }

    public String getOSVersion() {
        if (osVersion == null) {
            osVersion = System.getProperty("os.version");
        }
        return osVersion;
    }

    public String getOSArch() {
        if (osArch == null) {
            osArch = System.getProperty("os.arch");
        }
        return osArch;
    }

    public String getFileSeparator() {
        if (fileSeparator == null) {
            fileSeparator = System.getProperty("file.separator");
        }
        return fileSeparator;
    }

    public String getPathSeparator() {
        if (pathSeparator == null) {
            pathSeparator = System.getProperty("path.separator");
        }
        return pathSeparator;
    }

    public String getLineSeparator() {
        if (lineSeparator == null) {
            lineSeparator = System.getProperty("line.separator");
        }
        return lineSeparator;
    }

    public String getUserName() {
        if (userName == null) {
            userName = System.getProperty("user.name");
        }
        return userName;
    }

    public String getUserHome() {
        if (userHome == null) {
            userHome = System.getProperty("user.home");
        }
        return userHome;
    }

    public String getUserDir() {
        if (userDir == null) {
            userDir = System.getProperty("user.dir");
        }
        return userDir;
    }

    public void executeShellCommand(String command) throws IOException {
        Runtime.getRuntime().exec(command);
    }
}
