package com.github.intelligence.orchestrator.command;

import com.github.intelligence.orchestrator.services.command.CommandService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class CommandLineServiceTest {
    private final String appRootCommand = "app";
    private final String appRootDescription = "This is a test application";
    private final String appRootVersion = "1.0.0";
    private CommandService commandLineService;
    private final String subcommandName = "test-subcommand";
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

        commandLineService = new CommandService(
                appRootCommand,
                appRootVersion,
                appRootDescription
        );
    }

    public void addSubcommand() {
        commandLineService.addSubcommand(subcommandName, subcommandVersion, subcommandDescription, () -> {});
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    void printsRootCommandVersionForRootCommandVersionOptionShort() {
        String[] args = {"-v"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(appRootVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandVersionForRootCommandVersionOptionLong() {
        String[] args = {"--version"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(appRootVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandWithNoArguments() {
        String[] args = {};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandWithHelpAsOnlyArgument() {
        String[] args = {"help"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandHelpOptionShort() {
        String[] args = {"-h"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsRootCommandUsageForRootCommandHelpOptionLong() {
        String[] args = {"--help"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownRootCommandOptionShort() {
        String badShortOption = "-bad-short-option";
        String[] args = {badShortOption};
        commandLineService.run(args);

        assertTrue(errContent.toString().contains(badShortOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownRootCommandOptionLong() {
        String badLongOption = "--bad-long-option";
        String[] args = {badLongOption};
        commandLineService.run(args);

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void detectsIntendedRootCommandOptionLongAndExecutesOptionLongSuccessfullyWhenUserConfirmsYes() {
        String input = "YES";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String[] args = {"--halp"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains("Did you mean '--help'?"));

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));
    }

    @Test
    void detectsIntendedRootCommandOptionLongAndPrintsActionableUsageWhenUserConfirmsNo() {
        String input = "NO";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        String badLongOption = "--halp";
        String[] args = {badLongOption};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains("Did you mean '--help'?"));

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));
    }

    @Test
    void printsActionableUsageForRootCommandUnmatchedParameter() {
        String unmatchedParameter = "bad-command";
        String[] args = {unmatchedParameter};
        commandLineService.run(args);

        assertTrue(errContent.toString().contains(unmatchedParameter));
        assertTrue(errContent.toString().contains("Please refer to the 'Commands' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void addedSubcommandIsIncludedInRootCommandUsage() {
        addSubcommand();

        String[] args = {};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(appRootCommand));
        assertTrue(outContent.toString().contains(appRootDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertTrue(outContent.toString().contains(subcommandName));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void detectsIntendedSubcommandForRootCommandAndExecutesSubcommandSuccessfullyWhenUserConfirmsYes() {
        String input = "YES";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        addSubcommand();
        String[] args = {"test-subcomma"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains("Did you mean '" + subcommandName + "'?"));

        assertTrue(outContent.toString().contains(subcommandName));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));
    }

    @Test
    void detectsIntendedSubcommandForRootCommandAndPrintsActionableUsageWhenUserConfirmsNo() {
        String input = "NO";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        addSubcommand();
        String badLongOption = "test-subcomma";
        String[] args = {badLongOption};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains("Did you mean '" + subcommandName + "'?"));

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Commands' section"));

        assertTrue(errContent.toString().contains(appRootCommand));
        assertTrue(errContent.toString().contains(appRootDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));
    }

    @Test
    void printsSubcommandVersionForSubcommandVersionOptionShort() {
        addSubcommand();

        String[] args = {subcommandName, "-v"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(subcommandVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandVersionForSubcommandVersionOptionLong() {
        addSubcommand();

        String[] args = {subcommandName, "--version"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(subcommandVersion));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandUsageForSubcommandWithNoArguments() {
        addSubcommand();

        String[] args = {subcommandName};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(subcommandName));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandUsageForSubcommandWithHelpAsOnlyArgument() {
        addSubcommand();

        String[] args = {subcommandName, "help"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(subcommandName));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandUsageForSubcommandHelpOptionShort() {
        addSubcommand();

        String[] args = {subcommandName, "-h"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(subcommandName));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsSubcommandUsageForSubcommandHelpOptionLong() {
        addSubcommand();

        String[] args = {subcommandName, "--help"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(subcommandName));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownSubcommandOptionShort() {
        addSubcommand();

        String badShortOption = "-bad-short-option";
        String[] args = {subcommandName, badShortOption};
        commandLineService.run(args);

        assertTrue(errContent.toString().contains(badShortOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(subcommandName));
        assertTrue(errContent.toString().contains(subcommandDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void printsActionableUsageForUnknownSubcommandOptionLong() {
        addSubcommand();

        String badLongOption = "--bad-long-option";
        String[] args = {subcommandName, badLongOption};
        commandLineService.run(args);

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(subcommandName));
        assertTrue(errContent.toString().contains(subcommandDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void detectsIntendedSubcommandOptionLongAndExecutesOptionLongSuccessfullyWhenUserConfirmsYes() {
        String input = "YES";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        addSubcommand();
        String[] args = {subcommandName, "--halp"};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains("Did you mean '--help'?"));

        assertTrue(outContent.toString().contains(subcommandName));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));
    }

    @Test
    void detectsIntendedSubcommandOptionLongAndPrintsActionableUsageWhenUserConfirmsNo() {
        String input = "NO";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        addSubcommand();
        String badLongOption = "--halp";
        String[] args = {subcommandName, badLongOption};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains("Did you mean '--help'?"));

        assertTrue(errContent.toString().contains(badLongOption));
        assertTrue(errContent.toString().contains("Please refer to the 'Options' section"));

        assertTrue(errContent.toString().contains(subcommandName));
        assertTrue(errContent.toString().contains(subcommandDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));
    }

    @Test
    void printsActionableUsageForSubcommandUnmatchedParameter() {
        addSubcommand();

        String unmatchedParameter = "bad-command";
        String[] args = {subcommandName, unmatchedParameter};
        commandLineService.run(args);

        assertTrue(errContent.toString().contains(unmatchedParameter));
        assertTrue(errContent.toString().contains("Please refer to the 'Commands' section"));

        assertTrue(errContent.toString().contains(subcommandName));
        assertTrue(errContent.toString().contains(subcommandDescription));

        assertTrue(errContent.toString().contains("-h"));
        assertTrue(errContent.toString().contains("--help"));

        assertTrue(errContent.toString().contains("-v"));
        assertTrue(errContent.toString().contains("--version"));

        assertEquals(outContent.toString(), "");
    }

    @Test
    void addedParameterForSubcommandIsIncludedInSubcommandUsage() {
        String subcommandParameter = "test-parameter";
        String subcommandParameterDescription = "A sample parameter for testing purposes.";

        commandLineService.addSubcommand(subcommandName, subcommandVersion, subcommandDescription, () -> {});
        commandLineService.addSubcommandParameter(subcommandName, subcommandParameter, String.class, subcommandParameterDescription);

        String[] args = {subcommandName};
        commandLineService.run(args);

        assertTrue(outContent.toString().contains(subcommandName));
        assertTrue(outContent.toString().contains(subcommandDescription));

        assertTrue(outContent.toString().contains(subcommandParameter));
        assertTrue(outContent.toString().contains(subcommandParameterDescription));

        assertTrue(outContent.toString().contains("-h"));
        assertTrue(outContent.toString().contains("--help"));

        assertTrue(outContent.toString().contains("-v"));
        assertTrue(outContent.toString().contains("--version"));

        assertEquals(errContent.toString(), "");
    }
}
