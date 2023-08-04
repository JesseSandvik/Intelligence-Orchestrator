package com.github.intelligence.orchestrator.picocli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PicocliServiceTest {
    private final PicocliService picoService = new PicocliService("app", "App Version 1.0.0");
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
    void printUsageForExecutionWithNoArguments() {
        picoService.run();

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printUsageForHelpOptionShort() {
        picoService.run("-h");

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printUsageForHelpOptionLong() {
        picoService.run("--help");

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printVersionForVersionOptionShort() {
        picoService.run("-V");

        assertTrue(outContent.toString().contains("Version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printUsageForVersionOptionLong() {
        picoService.run("--version");

        assertTrue(outContent.toString().contains("Version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printUsageForUnmatchedArgument() {
        picoService.run("testing123");

        assertTrue(errContent.toString().contains("Unmatched argument at index 0: 'testing123'"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printUsageForUnknownOptionShort() {
        picoService.run("-do-something");

        assertTrue(errContent.toString().contains("Unknown option: '-do-something'"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printUsageForUnknownOptionLong() {
        picoService.run("--do-something");

        assertTrue(errContent.toString().contains("Unknown option: '--do-something'"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void usageIncludesAddedSubcommand() {
        picoService.addSubcommand("test-subcommand");
        picoService.run();

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertTrue(outContent.toString().contains("Commands"));
        assertTrue(outContent.toString().contains("test-subcommand"));

        assertEquals(errContent.toString(), "");
    }
}
