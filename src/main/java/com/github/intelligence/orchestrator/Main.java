package com.github.intelligence.orchestrator;


import com.github.intelligence.orchestrator.system.operatingSystem.OperatingSystemServiceUtility;

public class Main {
    public static void main(String[] args) {
        OperatingSystemServiceUtility os = new OperatingSystemServiceUtility();

        System.out.println(os.getOSName());
        System.out.println(os.getOSVersion());
        System.out.println(os.getOSArch());

        System.out.println(os.getFileSeparator());
        System.out.println(os.getPathSeparator());
        System.out.println(os.getLineSeparator());

        System.out.println(os.getUserName());
        System.out.println(os.getUserHome());
        System.out.println(os.getUserDir());
    }
}
