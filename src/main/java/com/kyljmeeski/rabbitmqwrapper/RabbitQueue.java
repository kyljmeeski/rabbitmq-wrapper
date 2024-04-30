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
