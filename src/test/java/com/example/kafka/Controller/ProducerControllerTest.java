package com.example.kafka.Controller;

import com.example.kafka.models.Data;
import com.example.kafka.service.serviceImpl.KafkaProducerServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.Assert.*;

public class ProducerControllerTest {

    @Mock
    KafkaProducerServiceImpl kafkaProducerService;

    ProducerController producerController;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
       producerController=new ProducerController(kafkaProducerService);

    }

    @Test
    public void startProducer() {
        String resp=producerController.startProducer();
        assertEquals("started",resp);
    }

    @Test
    public void stopProducer() {
        String resp=producerController.stopProducer();
        assertEquals("stopped",resp);
    }

    @Test
    public void interruptProducer() {
        String resp=producerController.interruptProducer();
        assertEquals("interruptted",resp);
    }
}