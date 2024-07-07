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

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * Utility class for declaring RabbitMQ queues.
 */
public class Queues {

    private final Connection connection;

    /**
     * Constructs a {@code Queues} utility with the specified RabbitMQ connection factory.
     *
     * @param connection the RabbitMQ connection
     */
    public Queues(Connection connection) {
        this.connection = connection;
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
        Channel channel = connection.createChannel();
        channel.queueDeclare(name, durable, exclusive, autoDelete, args);
        channel.close();
        connection.close();
        return new RabbitQueue(connection, name);
    }

}
