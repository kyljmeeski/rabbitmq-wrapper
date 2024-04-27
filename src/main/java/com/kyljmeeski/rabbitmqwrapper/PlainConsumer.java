package com.kyljmeeski.rabbitmqwrapper;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class PlainConsumer implements Consumer {

    private final ConnectionFactory factory;

    public PlainConsumer(ConnectionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void startConsuming(String queue, java.util.function.Consumer<String> consumer) throws IOException, TimeoutException {
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        DeliverCallback callback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            consumer.accept(message);
        };
        channel.basicConsume(queue, true, callback, consumerTag -> {});
    }

}
