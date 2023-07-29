package com.github.intelligence.orchestrator;


import com.github.intelligence.orchestrator.system.OperatingSystem;

public class Main {
    public static void main(String[] args) {
        OperatingSystem os = new OperatingSystem();

        System.out.println(os.getOSName());
        System.out.println(os.getOSVersion());
        System.out.println(os.getUserHome());
        System.out.println(os.getUserDir());
    }
}
