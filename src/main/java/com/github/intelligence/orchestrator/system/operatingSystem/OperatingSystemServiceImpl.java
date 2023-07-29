package com.github.intelligence.orchestrator.system.operatingSystem;

import java.io.IOException;

public class OperatingSystemServiceImpl implements OperatingSystemService {
    private final OperatingSystemServiceUtility osUtility;
    public OperatingSystemServiceImpl() {
        this.osUtility = new OperatingSystemServiceUtility();
    }
    @Override
    public String getOperatingSystemTmpDir() {
        return osUtility.getTmpDir();
    }
    @Override
    public String getOperatingSystemName() {
        return osUtility.getOSName();
    }

    @Override
    public String getOperatingSystemVersion() {
        return osUtility.getOSVersion();
    }

    @Override
    public String getOperatingSystemArchitecture() {
        return osUtility.getOSArch();
    }

    @Override
    public String getOperatingSystemFileSeparator() {
        return osUtility.getFileSeparator();
    }

    @Override
    public String getOperatingSystemPathSeparator() {
        return osUtility.getPathSeparator();
    }

    @Override
    public String getOperatingSystemLineSeparator() {
        return osUtility.getLineSeparator();
    }

    @Override
    public String getOperatingSystemUserName() {
        return osUtility.getUserName();
    }

    @Override
    public String getOperatingSystemUserHome() {
        return osUtility.getUserHome();
    }

    @Override
    public String getOperatingSystemUserDir() {
        return osUtility.getUserDir();
    }

    @Override
    public void executeShellCommand(String command) throws IOException {
        osUtility.executeShellCommand(command);
    }
}
