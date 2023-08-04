package com.github.intelligence.orchestrator.properties;

import java.io.IOException;

public class PropertiesService implements PropertiesServiceContract {
    private static PropertiesServiceUtility propertiesService;

    public PropertiesService(String propertiesFilePath) throws IOException {
        propertiesService = new PropertiesServiceUtility(propertiesFilePath);
    }

    @Override
    public String getProperty(String propertyKey) {
        return propertiesService.getProperty(propertyKey);
    }
}
