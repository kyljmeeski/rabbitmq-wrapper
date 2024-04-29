package com.kyljmeeski.rabbitmqwrapper;

/**
 * Represents a RabbitMQ exchange.
 */
public class RabbitExchange {


    private final String name;

    /**
     * Constructs a {@code RabbitExchange} with the specified exchange name.
     *
     * @param name the name of the RabbitMQ exchange
     */
    public RabbitExchange(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the RabbitMQ exchange.
     *
     * @return the name of the exchange
     */
    public String name() {
        return name;
    }

}
