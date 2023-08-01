package com.github.intelligence.orchestrator.system.operatingSystem;

import java.io.IOException;

public class OperatingSystemService implements OperatingSystemServiceContract {
    private final OperatingSystemServiceUtility osUtility;
    public OperatingSystemService() {
        this.osUtility = new OperatingSystemServiceUtility();
    }

    @Override
    public String getOSTempDirectory() {
        return osUtility.getTempDirectory();
    }

    @Override
    public String getOSName() {
        return osUtility.getOSName();
    }

    @Override
    public String getOSVersion() {
        return osUtility.getOSVersion();
    }

    @Override
    public String getOSArchitecture() {
        return osUtility.getOSArchitecture();
    }

    @Override
    public String getFileSeparator() {
        return osUtility.getFileSeparator();
    }

    @Override
    public String getPathSeparator() {
        return osUtility.getPathSeparator();
    }

    @Override
    public String getLineSeparator() {
        return osUtility.getLineSeparator();
    }

    @Override
    public String getOSUserName() {
        return osUtility.getUserName();
    }

    @Override
    public String getOSUserHome() {
        return osUtility.getUserHome();
    }

    @Override
    public String getOSUserDirectory() {
        return osUtility.getUserDirectory();
    }

    @Override
    public void executeShellCommand(String command) throws IOException {
        osUtility.executeShellCommand(command);
    }
}
