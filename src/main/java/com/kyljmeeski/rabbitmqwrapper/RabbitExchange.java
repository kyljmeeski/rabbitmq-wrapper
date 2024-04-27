package com.kyljmeeski.rabbitmqwrapper;

public class RabbitExchange {

    private final String name;

    public RabbitExchange(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

}
