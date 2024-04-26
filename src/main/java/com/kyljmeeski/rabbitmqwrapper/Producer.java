package com.kyljmeeski.rabbitmqwrapper;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface Producer {

    void produce(String message) throws IOException, TimeoutException;

}
