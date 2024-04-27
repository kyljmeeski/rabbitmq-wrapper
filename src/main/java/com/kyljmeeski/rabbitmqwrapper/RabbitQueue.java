package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitQueue {

    private final ConnectionFactory factory;
    private final String name;

    public RabbitQueue(ConnectionFactory factory, String name) {
        this.factory = factory;
        this.name = name;
    }

    public String name() {
        return name;
    }

    public RabbitQueue bind(RabbitExchange exchange, String routingKey) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueBind(name, exchange.name(), routingKey);
        channel.close();
        return this;
    }

}
