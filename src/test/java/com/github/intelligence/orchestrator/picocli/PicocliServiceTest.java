package com.github.intelligence.orchestrator.picocli;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PicocliServiceTest {
    private final String appRootCommand = "app";
    private final String appRootName = "test-app";
    private final String appRootDescription = "This is a test application";
    private final String appRootVersion = "1.0.0";
    private final PicocliService picoService = new PicocliService(
            appRootCommand,
            appRootName,
            appRootDescription,
            appRootVersion
    );
    private final String subcommandCommand = "test-command";
    private final String subcommandDescription = "This is a sample subcommand for testing.";
    private final String subcommandVersion = "1.0.0";
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
        picoService.run("-V");

        assertTrue(outContent.toString().contains(appRootName));
        assertTrue(outContent.toString().contains(appRootVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandVersionForRootCommandVersionOptionLong() {
        picoService.run("--version");

        assertTrue(outContent.toString().contains(appRootName));
        assertTrue(outContent.toString().contains(appRootVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandWithNoArguments() {
        picoService.run();

        assertTrue(outContent.toString().contains(appRootCommand));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandHelpOptionShort() {
        picoService.run("-h");

        assertTrue(outContent.toString().contains(appRootCommand));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandHelpOptionLong() {
        picoService.run("--help");

        assertTrue(outContent.toString().contains(appRootCommand));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownRootCommandOptionShort() {
        String badShortOption = "-bad-short-option";
        picoService.run(badShortOption);

        assertTrue(errContent.toString().contains(badShortOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownRootCommandOptionLong() {
        String badLongOption = "--bad-long-option";
        picoService.run(badLongOption);

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForRootCommandUnmatchedParameter() {
        String unmatchedParameter = "bad-command";
        picoService.run(unmatchedParameter);

        assertTrue(errContent.toString().contains(unmatchedParameter));
        assertTrue(errContent.toString().contains("Please refer to the 'Commands' section"));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-V"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void addedSubcommandIsIncludedInRootCommandUsageInformation() {

        picoService.addSubcommand(subcommandCommand, subcommandDescription, subcommandVersion, () -> {
        });
        picoService.run();

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-V"));
        assertTrue(outContent.toString().contains("--version"));

        assertTrue(outContent.toString().contains("Commands:"));
        assertTrue(outContent.toString().contains(subcommandCommand));

        assertEquals(errContent.toString(), "");
    }
}
