package com.github.intelligence.orchestrator.properties;

import org.junit.jupiter.api.Test;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class PropertiesServiceTest {

    @Test
    void returnsPropertyFromResourcesFile() throws IOException {
        PropertiesService propertiesService = new PropertiesService("/test.properties");
        String nameProperty = propertiesService.getProperty("name");
        String versionProperty = propertiesService.getProperty("version");

        assertEquals("test", nameProperty);
        assertEquals("1.0.0", versionProperty);
    }
}
