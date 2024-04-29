package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Utility class for declaring RabbitMQ exchanges.
 */
public class Exchanges {

    private final ConnectionFactory factory;

    /**
     * Constructs an {@code Exchanges} utility with the specified RabbitMQ connection factory.
     *
     * @param factory the RabbitMQ connection factory used to create connections
     */
    public Exchanges(ConnectionFactory factory) {
        this.factory = factory;
    }

    /**
     * Declares a RabbitMQ exchange with the given name and type.
     *
     * @param name the name of the exchange
     * @param type the type of the exchange (e.g., "direct", "topic", "fanout", etc.)
     * @return a {@link RabbitExchange} object representing the declared exchange
     * @throws IOException      if an error occurs while communicating with RabbitMQ
     * @throws TimeoutException if the operation times out while waiting for a response from RabbitMQ
     */
    public RabbitExchange declare(String name, String type) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(name, type);
        channel.close();
        return new RabbitExchange(name);
    }

}
