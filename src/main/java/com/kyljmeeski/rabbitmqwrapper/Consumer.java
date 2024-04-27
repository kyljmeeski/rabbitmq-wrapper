package com.kyljmeeski.rabbitmqwrapper;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface Consumer {

    void startConsuming(String queue, java.util.function.Consumer<String> consumer) throws IOException, TimeoutException;

}
