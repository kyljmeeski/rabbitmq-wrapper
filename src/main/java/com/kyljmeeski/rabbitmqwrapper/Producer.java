package com.kyljmeeski.rabbitmqwrapper;

public interface Producer<T> {

    void produce(T message);

}
