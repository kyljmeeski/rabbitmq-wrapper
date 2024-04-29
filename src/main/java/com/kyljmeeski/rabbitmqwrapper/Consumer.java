package com.kyljmeeski.rabbitmqwrapper;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * A functional interface for consuming messages from a RabbitMQ queue.
 */
public interface Consumer {

    /**
     * Starts consuming messages from the queue.
     *
     * @throws IOException      if an error occurs while communicating with RabbitMQ
     * @throws TimeoutException if the operation times out while waiting for a response from RabbitMQ
     */
    void startConsuming() throws IOException, TimeoutException;

}
