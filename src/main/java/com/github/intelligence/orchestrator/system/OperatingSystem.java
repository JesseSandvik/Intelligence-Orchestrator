package com.github.intelligence.orchestrator.system;

public class OperatingSystem {
    private static String OSName;
    private static String OSVersion;
    private static String userHome;
    private static String userDir;
    public String getOSName() {
        if (OSName == null) {
            OSName = System.getProperty("os.name");
        }
        return OSName;
    }
    public String getOSVersion() {
        if (OSVersion == null) {
            OSVersion = System.getProperty("os.version");
        }
        return OSVersion;
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
    public void getAllProperties() {
        System.getProperties().list(System.out);
    }
}
