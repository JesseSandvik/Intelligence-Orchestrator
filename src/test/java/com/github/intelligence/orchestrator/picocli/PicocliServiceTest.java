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
    void subcommandIsIncludedInUsageInformation() {
        String subCmdName = "test123";
        String subCmdVersion = subCmdName + " Version 1.0.0";
        picoService.addSubcommand(subCmdName, subCmdVersion,() -> {});
        picoService.run();

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertTrue(outContent.toString().contains("Commands:"));
        assertTrue(outContent.toString().contains(subCmdName));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsActionableUsageForUnknownOption() {
        String subCmdName = "test123";
        String subCmdVersion = subCmdName + " Version 1.0.0";
        picoService.addSubcommand(subCmdName, subCmdVersion,() -> {});
        picoService.run("--bad-option");

        assertTrue(errContent.toString().contains("--bad-option"));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertTrue(errContent.toString().contains("Commands:"));
        assertTrue(errContent.toString().contains("test123"));

        assertEquals("", outContent.toString());
    }

    @Test
    void printsActionableUsageForUnmatchedParameter() {
        String subCmdName = "test123";
        String subCmdVersion = subCmdName + " Version 1.0.0";
        picoService.addSubcommand(subCmdName, subCmdVersion,() -> {});
        picoService.run("bad-command");

        assertTrue(errContent.toString().contains("bad-command"));
        assertTrue(errContent.toString().contains("Please refer to the 'Commands' section"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertTrue(errContent.toString().contains("Commands:"));
        assertTrue(errContent.toString().contains("test123"));

        assertEquals("", outContent.toString());
    }

    @Test
    void printsSubcommandVersionForSubcommandVersionOptionShort() {
        String subCmdName = "test123";
        String subCmdVersion = subCmdName + " Version 1.0.0";

        picoService.addSubcommand(subCmdName, subCmdVersion,() -> {});
        String[] args = {subCmdName, "-V"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subCmdName));
        assertTrue(outContent.toString().contains(subCmdVersion));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsSubcommandVersionForSubcommandVersionOptionLong() {
        String subCmdName = "test123";
        String subCmdVersion = subCmdName + " Version 1.0.0";

        picoService.addSubcommand(subCmdName, subCmdVersion,() -> {});
        String[] args = {subCmdName, "--version"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subCmdName));
        assertTrue(outContent.toString().contains(subCmdVersion));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsSubcommandUsageForSubcommandHelpOptionShort() {
        String subCmdName = "test123";
        String subCmdVersion = subCmdName + " Version 1.0.0";
        String subCmdParamLabel = "do-this";
        String subCmdParamDescription = "I want to do something.";

        picoService.addSubcommand(subCmdName, subCmdVersion,() -> {});
        picoService.addParameterForSubcommand(subCmdName, subCmdParamLabel, String.class, subCmdParamDescription);
        String[] args = {subCmdName, "-h"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subCmdName));
        assertTrue(outContent.toString().contains(subCmdParamLabel));
        assertTrue(outContent.toString().contains(subCmdParamDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals("", errContent.toString());
    }

    @Test
    void printsSubcommandUsageForSubcommandHelpOptionLong() {
        String subCmdName = "test123";
        String subCmdVersion = subCmdName + " Version 1.0.0";
        String subCmdParamLabel = "do-this";
        String subCmdParamDescription = "I want to do something.";

        picoService.addSubcommand(subCmdName, subCmdVersion,() -> {});
        picoService.addParameterForSubcommand(subCmdName, subCmdParamLabel, String.class, subCmdParamDescription);
        String[] args = {subCmdName, "--help"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subCmdName));
        assertTrue(outContent.toString().contains(subCmdParamLabel));
        assertTrue(outContent.toString().contains(subCmdParamDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals("", errContent.toString());
    }
}
