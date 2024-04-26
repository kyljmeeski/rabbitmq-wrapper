package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

public class Queues {

    private final ConnectionFactory factory;

    public Queues(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void declare(String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> args) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(name, durable, exclusive, autoDelete, args);
        channel.close();
    }

    public void bind(String queue, String exchange, String routingKey) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueBind(queue, exchange, routingKey);
        channel.close();
    }

}
