package com.example.kafka.service;

public interface KafkaProducerService {

    void startProducer() throws InterruptedException;

    void runProducer() throws InterruptedException;

    void stopProducer();

    void interruptProducer();
}
