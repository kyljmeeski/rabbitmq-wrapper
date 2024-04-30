/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2024 Amir Syrgabaev
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
