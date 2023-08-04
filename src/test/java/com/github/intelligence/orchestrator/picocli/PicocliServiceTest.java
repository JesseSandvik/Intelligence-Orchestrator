package com.github.intelligence.orchestrator.picocli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PicocliServiceTest {
    private final PicocliService picoService = new PicocliService("app", "app Version 1.0.0");
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void printsUsageForNoArguments() {
        picoService.run();

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsUsageForHelpOptionShort() {
        picoService.run("-h");

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsUsageForHelpOptionLong() {
        picoService.run("--help");

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsVersionForVersionOptionShort() {
        picoService.run("-V");

        assertTrue(outContent.toString().contains("app"));
        assertTrue(outContent.toString().contains("Version 1.0.0"));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsVersionForVersionOptionLong() {
        picoService.run("--version");

        assertTrue(outContent.toString().contains("app"));
        assertTrue(outContent.toString().contains("Version 1.0.0"));

        assertEquals("", errContent.toString());
    }
}
