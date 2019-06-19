package com.example.kafka.service.serviceImpl;

import com.example.kafka.models.Data;
import com.example.kafka.models.FinancialDataMaster;
import com.example.kafka.repositories.FinancialDataMasterRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class KafkaConsumerServiceImplTest {

    KafkaConsumerServiceImpl kafkaConsumerService;

    @Mock
    FinancialDataMasterRepository financialDataMasterRepository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        kafkaConsumerService=new KafkaConsumerServiceImpl(financialDataMasterRepository);
    }


    @Test
    public void getDataByIdName() {
        FinancialDataMaster f=new FinancialDataMaster();
        f.setName("Adobe");
        when(financialDataMasterRepository.findFirstByNameOrderByAsOfDesc("Adobe")).thenReturn(f);
    }
}