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
 * A plain implementation of the {@link Producer} interface that produces messages
 * to a RabbitMQ exchange with a specified routing key.
 */
public class PlainProducer implements Producer {

    private final ConnectionFactory factory;
    private final RabbitExchange exchange;
    private final String routingKey;

    /**
     * Constructs a {@code PlainProducer} with the specified RabbitMQ connection factory,
     * exchange, and routing key.
     *
     * @param factory    the RabbitMQ connection factory used to create connections
     * @param exchange   the RabbitMQ exchange to publish messages to
     * @param routingKey the routing key for the exchange
     */
    public PlainProducer(ConnectionFactory factory, RabbitExchange exchange, String routingKey) {
        this.factory = factory;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    /**
     * Produces a message to the RabbitMQ exchange with the specified routing key.
     *
     * @param message the message to be published
     * @throws IOException      if an error occurs while communicating with RabbitMQ
     * @throws TimeoutException if the operation times out while waiting for a response from RabbitMQ
     */
    @Override
    public void produce(String message) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish(exchange.name(), routingKey, null, message.getBytes());
        channel.close();
    }

}
