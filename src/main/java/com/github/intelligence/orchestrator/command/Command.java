package com.github.intelligence.orchestrator.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class Command implements Callable<Integer> {
    private final String name;
    private String version;
    private String usageDescriptionSynopsis;
    private String usageDescriptionFull;
    private List<Parameter> parameters = new ArrayList<>();
    private List<Option> options = new ArrayList<>();
    private List<Command> subcommands = new ArrayList<>();
    private Map<String, Object> valuesToPositionalParameterLabels = new LinkedHashMap<>();
    private Map<String, Object> valuesToOptionNames = new LinkedHashMap<>();

    public Command(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUsageDescriptionSynopsis() {
        return usageDescriptionSynopsis;
    }

    public void setUsageDescriptionSynopsis(String usageDescriptionSynopsis) {
        this.usageDescriptionSynopsis = usageDescriptionSynopsis;
    }

    public String getUsageDescriptionFull() {
        return usageDescriptionFull;
    }

    public void setUsageDescriptionFull(String usageDescriptionFull) {
        this.usageDescriptionFull = usageDescriptionFull;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Command> getSubcommands() {
        return subcommands;
    }

    public void setSubcommands(List<Command> subcommands) {
        this.subcommands = subcommands;
    }

    public void mapValueToPositionalParameterLabel(String positionalParameterLabel, Object value) {
        valuesToPositionalParameterLabels.put(positionalParameterLabel, value);
    }

    public void mapValueToOptionName(String optionName, Object value) {
        valuesToOptionNames.put(optionName, value);
    }

    @Override
    public Integer call() {
        return 0;
    }
}
