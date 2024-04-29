package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Represents a RabbitMQ queue.
 */
public class RabbitQueue {

    private final ConnectionFactory factory;
    private final String name;

    /**
     * Constructs a {@code RabbitQueue} with the specified RabbitMQ connection factory and queue name.
     *
     * @param factory the RabbitMQ connection factory used to create connections
     * @param name    the name of the RabbitMQ queue
     */
    public RabbitQueue(ConnectionFactory factory, String name) {
        this.factory = factory;
        this.name = name;
    }

    /**
     * Gets the name of the RabbitMQ queue.
     *
     * @return the name of the queue
     */
    public String name() {
        return name;
    }

    /**
     * Binds the queue to the specified exchange with the given routing key.
     *
     * @param exchange   the RabbitMQ exchange to bind to
     * @param routingKey the routing key for the binding
     * @return this {@code RabbitQueue} object after binding
     * @throws IOException      if an error occurs while communicating with RabbitMQ
     * @throws TimeoutException if the operation times out while waiting for a response from RabbitMQ
     */
    public RabbitQueue bind(RabbitExchange exchange, String routingKey) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueBind(name, exchange.name(), routingKey);
        channel.close();
        return this;
    }

}
