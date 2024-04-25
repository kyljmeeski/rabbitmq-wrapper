package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PlainConsumer implements Consumer {

    private final ConnectionFactory factory;

    public PlainConsumer(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void startConsuming(String queue, Runnable action) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.basicConsume(queue, (consumerTag, delivery) -> action.run(), consumerTag -> {});
    }

}
