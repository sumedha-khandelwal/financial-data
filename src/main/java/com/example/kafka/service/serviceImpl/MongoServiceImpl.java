package com.example.kafka.service.serviceImpl;

import com.example.kafka.repositories.FinancialDataMasterRepository;
import com.example.kafka.repositories.FinancialDataRepository;
import com.example.kafka.service.MongoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MongoServiceImpl implements MongoService {

    @Autowired
    private MongoClient client;

    @Autowired
    private Environment env;

    @Autowired
    private FinancialDataRepository financialDataRepository;

    @Override
    @Async
    public void updateMaster(String startTime,String endTime){
        List<DBObject> ops = new ArrayList<DBObject>();
        ops.add(new BasicDBObject("$out", "financial_data_master")); // writes to collection "target"
        DBCollection source = client.getDB(env.getProperty("spring.data.mongodb.database")).getCollection("financial_data");
        source.aggregate(ops);
        source.aggregate(Arrays.asList((DBObject)new BasicDBObject("$out", "financial_data_master")));
        financialDataRepository.deleteAllByAsOfBetween(startTime,endTime);
    }
}
