package com.github.intelligence.orchestrator.command;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

public class Command implements Callable<Integer> {
    private final String name;
    private final String version;
    private String usageDescriptionSynopsis;
    private String usageDescriptionFull;
    private final List<Parameter> parameters = new ArrayList<>();
    private final List<Option> options = new ArrayList<>();
    private final List<Command> subcommands = new ArrayList<>();
    private final Map<String, Object> valuesToPositionalParameters = new LinkedHashMap<>();
    private final Map<String, Object> valuesToLongOptionNames = new LinkedHashMap<>();

    public Command(String name, String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public String getUsageDescriptionSynopsis() {
        return usageDescriptionSynopsis;
    }

    public String getUsageDescriptionFull() {
        return usageDescriptionFull;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public List<Option> getOptions() {
        return options;
    }

    public List<Command> getSubcommands() {
        return subcommands;
    }

    public Map<String, Object> getValuesToPositionalParameters() {
        return valuesToPositionalParameters;
    }

    public Map<String, Object> getValuesToLongOptionNames() {
        return valuesToLongOptionNames;
    }

    public void setUsageDescriptionSynopsis(String usageDescriptionSynopsis) {
        this.usageDescriptionSynopsis = usageDescriptionSynopsis;
    }

    public void setUsageDescriptionFull(String usageDescriptionFull) {
        this.usageDescriptionFull = usageDescriptionFull;
    }

    public void addParameter(Parameter parameter) {
        parameters.add(parameter);
    }

    public void addOption(Option option) {
        options.add(option);
    }

    public void addSubcommand(Command subcommand) {
        subcommands.add(subcommand);
    }

    public void mapValueToPositionalParameterLabel(String positionalParameterLabel, Object value) {
        valuesToPositionalParameters.put(positionalParameterLabel, value);
    }

    public void mapValueToLongOptionName(String longOptionName, Object value) {
        valuesToLongOptionNames.put(longOptionName, value);
    }

    @Override
    public Integer call() {
        return 0;
    }
}
