package com.github.intelligence.orchestrator.system;

public class OperatingSystem {
    private static String OSName;
    private static String OSVersion;
    private static String OSArch;
    private static String fileSeparator;
    private static String pathSeparator;
    private static String lineSeparator;
    private static String userName;
    private static String userHome;
    private static String userDir;

    public String getOSName() {
        if (OSName != null) {
            OSName = System.getProperty("os.name");
        }
        return OSName;
    }

    public String getOSVersion() {
        if (OSVersion != null) {
            OSVersion = System.getProperty("os.version");
        }
        return OSVersion;
    }

    public String getOSArch() {
        if (OSArch != null) {
            OSArch = System.getProperty("os.arch");
        }
        return OSArch;
    }

    public String getFileSeparator() {
        if (fileSeparator != null) {
            fileSeparator = System.getProperty("file.separator");
        }
        return fileSeparator;
    }

    public static String getPathSeparator() {
        if (pathSeparator != null) {
            pathSeparator = System.getProperty("path.separator");
        }
        return pathSeparator;
    }

    public static String getLineSeparator() {
        if (lineSeparator != null) {
            lineSeparator = System.getProperty("line.separator");
        }
        return lineSeparator;
    }

    public static String getUserName() {
        if (userName != null) {
            userName = System.getProperty("user.name");
        }
        return userName;
    }

    public static String getUserHome() {
        if (userHome != null) {
            userHome = System.getProperty("user.home");
        }
        return userHome;
    }

    public static String getUserDir() {
        if (userDir != null) {
            userDir = System.getProperty("user.dir");
        }
        return userDir;
    }

    public void printAllProperties() {
        System.getProperties().list(System.out);
    }
}
