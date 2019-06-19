package com.example.kafka.Controller;

import com.example.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    KafkaProducerService kafkaProducerService;

    public ProducerController(KafkaProducerService kafkaProducerService){
        this.kafkaProducerService=kafkaProducerService;
    }

    @GetMapping("/start-producer")
    public String startProducer(){
        try{
            kafkaProducerService.startProducer();
            return "started";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }

    }

    @GetMapping("/stop-producer")
    public String stopProducer(){
        try{
            kafkaProducerService.stopProducer();
            return "stopped";
        }

         catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/interrupt-producer")
    public String interruptProducer(){
        try{
            kafkaProducerService.interruptProducer();
            return "interruptted";
        }
        catch (Exception e){
            e.printStackTrace();
            return "error";
        }

    }
}
