package com.example.kafka.service.serviceImpl;

import com.example.kafka.models.Data;
import com.example.kafka.models.FinancialData;
import com.example.kafka.models.FinancialDataMaster;
import com.example.kafka.repositories.FinancialDataMasterRepository;
import com.example.kafka.repositories.FinancialDataRepository;
import com.example.kafka.service.KafkaConsumerService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaConsumerServiceImpl implements KafkaConsumerService {

    @Autowired
    private FinancialDataRepository financialDataRepository;

    @Autowired
    private FinancialDataMasterRepository financialDataMasterRepository;

    @Autowired
    private Environment env;


     @KafkaListener(topics = "financial_instruments", groupId = "sample-group")
    public void consume(Data item){
         if(item.getStatus()!=null && ("running").equalsIgnoreCase(item.getStatus())){
             financialDataRepository.save(item.getFinancialData());
         }

         if(item.getStatus()!=null && ("close").equalsIgnoreCase(item.getStatus())){
             List<DBObject> ops = new ArrayList<DBObject>();
             ops.add(new BasicDBObject("$out", "financial_data_master")); // writes to collection "target"
             Integer port=env.getProperty("spring.data.mongodb.port",Integer.class);
             MongoClient client = new MongoClient(env.getProperty("spring.data.mongodb.host"),port);
             DBCollection source = client.getDB(env.getProperty("spring.data.mongodb.database")).getCollection("financial_data");
             source.aggregate(ops);
             source.aggregate(Arrays.asList((DBObject)new BasicDBObject("$out", "financial_data_master")));
             financialDataRepository.deleteAll();
         }

         if(item.getStatus()!=null && ("error").equalsIgnoreCase(item.getStatus())){
             financialDataRepository.deleteAll();
         }
    }

    @Override
    public FinancialDataMaster getDataByIdName(String name){
         return financialDataMasterRepository.findFirstByNameOrderByAsOfDesc(name);
    }
}
