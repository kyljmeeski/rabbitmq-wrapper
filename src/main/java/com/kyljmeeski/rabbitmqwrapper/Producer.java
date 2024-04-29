package com.kyljmeeski.rabbitmqwrapper;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * A functional interface for producing messages to a RabbitMQ exchange with a routing key.
 */
public interface Producer {

    /**
     * Produces a message to the RabbitMQ exchange with the specified routing key.
     *
     * @param message the message to be published
     * @throws IOException      if an error occurs while communicating with RabbitMQ
     * @throws TimeoutException if the operation times out while waiting for a response from RabbitMQ
     */
    void produce(String message) throws IOException, TimeoutException;

}
