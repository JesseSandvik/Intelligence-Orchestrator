package com.github.intelligence.orchestrator.services.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

class PropertiesServiceUtility {
    private Properties properties;

    public PropertiesServiceUtility(String propertiesFilePath) throws IOException {
        InputStream inputStream = null;
        try {
            properties = new Properties();
            inputStream = getClass().getResourceAsStream(propertiesFilePath);
            properties.load(inputStream);
        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    public String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }
}
