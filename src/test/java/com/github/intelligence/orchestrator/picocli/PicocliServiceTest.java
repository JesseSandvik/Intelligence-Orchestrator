package com.github.intelligence.orchestrator.picocli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PicocliServiceTest {
    private final String appRootCommand = "app";
    private final String appRootDescription = "This is a test application";
    private final String appRootVersion = "1.0.0";
    private final PicocliService picoService = new PicocliService(
            appRootCommand,
            appRootVersion,
            appRootDescription
    );
    private final String subcommand = "test-subcommand";
    private final String subcommandDescription = "This is a sample subcommand for testing";
    private final String subcommandVersion = "1.2.3";
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
    void printsRootCommandVersionForRootCommandVersionOptionShort() {
        String[] args = {"-V"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(appRootVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandVersionForRootCommandVersionOptionLong() {
        String[] args = {"--version"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(appRootVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandWithNoArguments() {
        String[] args = {};
        picoService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandHelpOptionShort() {
        String[] args = {"-h"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandHelpOptionLong() {
        String[] args = {"--help"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownRootCommandOptionShort() {
        String badShortOption = "-bad-short-option";
        String[] args = {badShortOption};
        picoService.run(args);

        assertTrue(errContent.toString().contains(badShortOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownRootCommandOptionLong() {
        String badLongOption = "--bad-long-option";
        String[] args = {badLongOption};
        picoService.run(args);

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForRootCommandUnmatchedParameter() {
        String unmatchedParameter = "bad-command";
        String[] args = {unmatchedParameter};
        picoService.run(args);

        assertTrue(errContent.toString().contains(unmatchedParameter));
        assertTrue(errContent.toString().contains("Please refer to the 'Commands' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void addedSubcommandIsIncludedInRootCommandUsage() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String[] args = {};
        picoService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertTrue(outContent.toString().contains(subcommand));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandVersionForSubcommandVersionOptionShort() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String[] args = {subcommand, "-V"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subcommandVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandVersionForSubcommandVersionOptionLong() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String[] args = {subcommand, "--version"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subcommandVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandUsageForSubcommandWithNoArguments() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String[] args = {subcommand};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subcommand));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandUsageForSubcommandHelpOptionShort() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String[] args = {subcommand, "-h"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subcommand));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandUsageForSubcommandHelpOptionLong() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String[] args = {subcommand, "--help"};
        picoService.run(args);

        assertTrue(outContent.toString().contains(subcommand));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownSubcommandOptionShort() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String badShortOption = "-bad-short-option";
        String[] args = {subcommand, badShortOption};
        picoService.run(args);

        assertTrue(errContent.toString().contains(badShortOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(subcommand));
        assertTrue(errContent.toString().contains(subcommandDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownSubcommandOptionLong() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String badLongOption = "--bad-long-option";
        String[] args = {subcommand, badLongOption};
        picoService.run(args);

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(subcommand));
        assertTrue(errContent.toString().contains(subcommandDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForSubcommandUnmatchedParameter() {
        picoService.addSubcommand(subcommand, subcommandVersion, subcommandDescription, () -> {});

        String unmatchedParameter = "bad-command";
        String[] args = {subcommand, unmatchedParameter};
        picoService.run(args);

        assertTrue(errContent.toString().contains(unmatchedParameter));
        assertTrue(errContent.toString().contains("Please refer to the 'Commands' section"));

        assertTrue(errContent.toString().contains(subcommand));
        assertTrue(errContent.toString().contains(subcommandDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }
}
