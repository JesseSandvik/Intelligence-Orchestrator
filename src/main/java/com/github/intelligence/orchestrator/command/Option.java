package com.github.intelligence.orchestrator.command;

public record Option(String[] names, String description, Class<?> type, String parameterLabel) {}
