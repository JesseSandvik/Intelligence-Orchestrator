package com.github.intelligence.orchestrator.picocli;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IOCommandTest {
    @Test
    void exitsWithCodeSuccessCodeZero() {
        IOCommand ioCommand = new IOCommand(null);

        assertEquals(ioCommand.call(), 0);
    }
}
