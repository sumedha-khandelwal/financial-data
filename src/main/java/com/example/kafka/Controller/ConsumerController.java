package com.example.kafka.Controller;

import com.example.kafka.models.FinancialDataMaster;
import com.example.kafka.service.KafkaConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    @GetMapping("/getPrice")
    public FinancialDataMaster getPrice(@RequestParam("name") String name){
        return kafkaConsumerService.getDataByIdName(name);
    }
}
