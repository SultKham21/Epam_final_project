package com.epam.pizzaplanet.controller.command;

public class CommandResult {
    private final RoutingType routingType;
    private final String page;

    public CommandResult(String page, RoutingType routingType) {
        this.routingType = routingType;
        this.page = page;
    }

    public RoutingType getRoutingType() {
        return routingType;
    }

    public String getPage() {
        return page;
    }

    public enum RoutingType {
        FORWARD, REDIRECT
    }
}
