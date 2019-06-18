package com.example.kafka.Controller;

import com.example.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    @GetMapping("/startProducer")
    public String startProducer(){
        kafkaProducerService.startProducer();
        return "started";
    }

    @GetMapping("/stopProducer")
    public String stopProducer(){
        kafkaProducerService.stopProducer();
        return "stopped";
    }

    @GetMapping("/interruptProducer")
    public String interruptProducer(){
        kafkaProducerService.interruptProducer();
        return "interruptted";
    }
}
