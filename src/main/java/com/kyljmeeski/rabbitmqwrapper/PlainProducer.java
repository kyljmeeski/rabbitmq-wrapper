package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PlainProducer implements Producer {

    private final ConnectionFactory factory;
    private final String exchange;
    private final String routingKey;

    public PlainProducer(ConnectionFactory factory, String exchange, String routingKey) {
        this.factory = factory;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    @Override
    public void produce(String message) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicPublish(exchange, routingKey, null, message.getBytes());
        channel.close();
    }

}
