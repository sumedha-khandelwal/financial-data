package com.example.kafka.service.serviceImpl;

import com.example.kafka.Controller.ConsumerController;
import com.example.kafka.models.Data;
import com.example.kafka.models.FinancialDataMaster;
import com.example.kafka.repositories.FinancialDataMasterRepository;
import com.example.kafka.repositories.FinancialDataRepository;
import com.example.kafka.service.KafkaConsumerService;
import com.example.kafka.service.MongoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Autowired
    private FinancialDataRepository financialDataRepository;

    private FinancialDataMasterRepository financialDataMasterRepository;
    private Logger logger = LoggerFactory.getLogger(ConsumerController.class.getName());

    @Autowired
    private Environment env;

    @Autowired
    private MongoService mongoService;

    public KafkaConsumerServiceImpl(FinancialDataMasterRepository financialDataMasterRepository){
        this.financialDataMasterRepository=financialDataMasterRepository;
    }

    private String startTime;
    private String endTime;

     @KafkaListener(topics = "financial_instruments", groupId = "sample-group")
    public void consume(Data item){
         if(item.getStatus()==null){
             return;
         }
         if(("started").equals(item.getStatus())){
             logger.info("Batch started");
             startTime=item.getFinancialData().getAsOf();
         }
         else if(("running").equalsIgnoreCase(item.getStatus()) && item.getFinancialData().getPayload().getPrice()!=null){
             financialDataRepository.save(item.getFinancialData());
         }

         else if(("close").equalsIgnoreCase(item.getStatus())){
             endTime=item.getFinancialData().getAsOf();
             logger.info("Batch Completed");
             mongoService.updateMaster(startTime,endTime);
         }

         else if(("error").equalsIgnoreCase(item.getStatus())){
             logger.info("Batch interruptteds");
             financialDataRepository.deleteAllByAsOfGreaterThanEqual(startTime);
         }
    }

    @Override
    public FinancialDataMaster getDataByIdName(String name){
         return financialDataMasterRepository.findFirstByNameOrderByAsOfDesc(name);
    }
}
