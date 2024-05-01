[![Java](https://img.shields.io/badge/Java-8%2B-orange)](https://www.oracle.com/java/)
![Lines of Code](https://img.shields.io/badge/lines_of_code-715-green)
![License](https://img.shields.io/badge/license-MIT-blue)

**RabbitMQ wrapper** is a simple and lightweight wrapper for official [RabbitMQ client](https://www.rabbitmq.com/client-libraries/java-client).

How to use:
```xml
<dependency>
    <groupId>io.github.kyljmeeski</groupId>
    <artifactId>rabbitmq-wrapper</artifactId>
    <version>0.0.1</version>
</dependency>
```

## Preparation
```java
ConnectionFactory factory = new ConnectionFactory();
factory.setPort(5672);
```
First, create and setup ConnectionFactory.

## Exchange Declaration
```java
Exchanges exchanges = new Exchanges(factory);
RabbitExchange exchange = exchanges.declare("vacancies", "direct");
```
Pass exchange name and type as arguments in constructor. 

## Queue Declaration
```java
Queues queues = new Queues(factory);
RabbitQueue queue = queues.declare(
        "vacancies-to-store", false, false, false, null
).bind(exchange, "to-store");
```
Queue declaration arguments are:
- name
- durable
- exclusive
- autoDelete
- arguments

You can bind queue with the exchange via the routing key.

## Producing Messages
```java
Producer producer = new PlainProducer(factory, exchange, "to-store");
producer.produce("hello");
```
Producer will produce a text message to the specified exchange with the routing key.

## Consuming Messages
```java
Consumer consumer = new PlainConsumer(factory, queue, System.out::println);
consumer.startConsuming();
```
 As a third argument you have to pass a consumer method, that will accept the delivered message
