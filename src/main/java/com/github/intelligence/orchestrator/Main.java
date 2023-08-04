package com.github.intelligence.orchestrator;

import com.github.intelligence.orchestrator.picocli.PicocliService;

public class Main {
    public static void main(String[] args) {
        PicocliService cliService = new PicocliService("io", "[IO] Version 1.0.0");
        cliService.run("-V");
    }
}
