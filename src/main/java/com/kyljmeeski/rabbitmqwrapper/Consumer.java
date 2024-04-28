package com.kyljmeeski.rabbitmqwrapper;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface Consumer {

    void startConsuming() throws IOException, TimeoutException;

}
