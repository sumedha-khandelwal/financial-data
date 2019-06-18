package com.example.kafka;

import com.example.kafka.models.FinancialData;
import com.example.kafka.repositories.FinancialDataMasterRepository;
import com.example.kafka.repositories.FinancialDataRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses ={ FinancialDataRepository.class, FinancialDataMasterRepository.class})
public class KafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }

}
