package com.example.kafka.service;

public interface KafkaProducerService {

    void startProducer();

    void runProducer();

    void stopProducer();

    void interruptProducer();
}
