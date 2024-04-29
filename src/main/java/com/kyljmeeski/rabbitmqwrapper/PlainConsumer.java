package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * A plain implementation of the {@link Consumer} interface that consumes messages from a RabbitMQ queue.
 */
public class PlainConsumer implements Consumer {

    private final ConnectionFactory factory;
    private final RabbitQueue queue;
    private final java.util.function.Consumer<String> consumer;

    /**
     * Constructs a {@code PlainConsumer} with the specified RabbitMQ connection factory,
     * queue, and message consumer.
     *
     * @param factory  the RabbitMQ connection factory used to create connections
     * @param queue    the RabbitMQ queue to consume messages from
     * @param consumer the consumer function that processes received messages
     */
    public PlainConsumer(ConnectionFactory factory, RabbitQueue queue, java.util.function.Consumer<String> consumer) {
        this.factory = factory;
        this.queue = queue;
        this.consumer = consumer;
    }

    /**
     * Starts consuming messages from the specified queue.
     *
     * @throws IOException      if an error occurs while communicating with RabbitMQ
     * @throws TimeoutException if the operation times out while waiting for a response from RabbitMQ
     */
    @Override
    public void startConsuming() throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            consumer.accept(message);
        };
        channel.basicConsume(queue.name(), true, callback, consumerTag -> {});
    }

}
