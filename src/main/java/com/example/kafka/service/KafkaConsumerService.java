package com.example.kafka.service;

import com.example.kafka.models.FinancialDataMaster;

public interface KafkaConsumerService {

    FinancialDataMaster getDataByIdName(String name);
}
