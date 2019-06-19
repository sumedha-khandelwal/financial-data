package com.example.kafka.Config;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class MongoConfig {
    @Autowired
    private Environment env;

    @Bean
    public MongoClient client() {
        return new MongoClient(env.getProperty("spring.data.mongodb.host"),env.getProperty("spring.data.mongodb.port",Integer.class));
    }
}
