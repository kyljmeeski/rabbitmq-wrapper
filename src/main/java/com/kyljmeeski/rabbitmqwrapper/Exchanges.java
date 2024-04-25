package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Exchanges {

    private final ConnectionFactory factory;

    public Exchanges(ConnectionFactory factory) {
        this.factory = factory;
    }

    public void declareAndBind(String name, String type) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(name, type);
        channel.close();
    }

}
