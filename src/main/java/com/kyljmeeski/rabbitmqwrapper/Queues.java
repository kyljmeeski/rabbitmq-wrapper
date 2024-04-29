package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Utility class for declaring RabbitMQ queues.
 */
public class Queues {

    private final ConnectionFactory factory;

    /**
     * Constructs a {@code Queues} utility with the specified RabbitMQ connection factory.
     *
     * @param factory the RabbitMQ connection factory used to create connections
     */
    public Queues(ConnectionFactory factory) {
        this.factory = factory;
    }

    /**
     * Declares a RabbitMQ queue with the specified parameters.
     *
     * @param name       the name of the queue
     * @param durable    {@code true} if the queue should survive a server restart, {@code false} otherwise
     * @param exclusive  {@code true} if the queue should be used by only one connection, {@code false} otherwise
     * @param autoDelete {@code true} if the queue should be deleted when there are no more consumers, {@code false} otherwise
     * @param args       additional arguments used for configuring the queue (can be {@code null})
     * @return a {@link RabbitQueue} object representing the declared queue
     * @throws IOException      if an error occurs while communicating with RabbitMQ
     * @throws TimeoutException if the operation times out while waiting for a response from RabbitMQ
     */
    public RabbitQueue declare(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> args) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(name, durable, exclusive, autoDelete, args);
        channel.close();
        return new RabbitQueue(factory, name);
    }

}
