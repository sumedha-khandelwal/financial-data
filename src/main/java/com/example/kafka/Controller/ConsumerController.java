package com.example.kafka.Controller;

import com.example.kafka.models.FinancialDataMaster;
import com.example.kafka.service.KafkaConsumerService;
import com.example.kafka.service.serviceImpl.KafkaProducerServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Autowired
    private KafkaConsumerService kafkaConsumerService;

    private Logger logger = LoggerFactory.getLogger(ConsumerController.class.getName());

    @GetMapping("/get-price")
    public FinancialDataMaster getPrice(@RequestParam("name") String name){
        try{
            FinancialDataMaster f=kafkaConsumerService.getDataByIdName(name);
            logger.info("Found the price:"+f);
            return f;
        }
        catch (Exception e){
            logger.error("Error while fetching the price",e);
            return null;
        }

    }
}
