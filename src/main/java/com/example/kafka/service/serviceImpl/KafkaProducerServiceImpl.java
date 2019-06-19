package com.example.kafka.service.serviceImpl;

import com.example.kafka.models.Data;
import com.example.kafka.models.FinancialData;
import com.example.kafka.models.Payload;
import com.example.kafka.service.KafkaProducerService;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Service
public class KafkaProducerServiceImpl implements KafkaProducerService {

    Logger logger = LoggerFactory.getLogger(KafkaProducerServiceImpl.class.getName());


    private KafkaTemplate<String, Data> kafkaTemplate;

    private KafkaTemplate<String, Data> kafkaTemplate2;

    public KafkaProducerServiceImpl(KafkaTemplate<String, Data> kafkaTemplate, KafkaTemplate<String, Data> kafkaTemplate2){
        this.kafkaTemplate=kafkaTemplate;
        this.kafkaTemplate2=kafkaTemplate2;
    }

    private volatile String flag="start";
    BlockingQueue<Data> msgQueue = new LinkedBlockingQueue<Data>();

    public void runProducer() throws InterruptedException {

        logger.info("Started Producer");


       Thread t1=new Thread(new Runnable() {
           @Override
           public void run() {
               while(flag.equals("running")) {

                createData1(msgQueue);
                createData2(msgQueue);
                createData3(msgQueue);
                createData4(msgQueue);

              }
           }
       });

       t1.start();

       Thread.sleep(1000);

        while (!(msgQueue.size() == 0) && !("close").equalsIgnoreCase(flag) && !("error").equalsIgnoreCase(flag)) {
            Data msg = null;
            try {
                msg = msgQueue.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (msg != null) {
                logger.info(msg.toString());
                kafkaTemplate.send(new ProducerRecord<String, Data>("financial_instruments", msg));
            }
        }
        logger.info("End of application");
    }

    private void createData1(BlockingQueue<Data> msgQueue) {
        Data d=new Data();
        d.setStatus("running");
        FinancialData fd=new FinancialData();
        fd.setName("Global Software For Geeks");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        fd.setAsOf(dateFormat.format(date));
        Payload p=new Payload();
        p.setPrice(100.0);
        fd.setPayload(p);
        d.setFinancialData(fd);
        msgQueue.add(d);

    }

    private void createData2(BlockingQueue<Data> msgQueue) {
        Data d=new Data();
        d.setStatus("running");
        FinancialData fd=new FinancialData();
        fd.setName("Microsoft");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        fd.setAsOf(dateFormat.format(date));
        Payload p=new Payload();
        p.setPrice(200.0);
        fd.setPayload(p);
        d.setFinancialData(fd);
        msgQueue.add(d);

    }

    private void createData3(BlockingQueue<Data> msgQueue) {
        Data d=new Data();
        d.setStatus("running");
        FinancialData fd=new FinancialData();
        fd.setName("Adobe");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        fd.setAsOf(dateFormat.format(date));
        Payload p=new Payload();
        p.setPrice(300.0);
        fd.setPayload(p);
        d.setFinancialData(fd);
        msgQueue.add(d);

    }

    private void createData4(BlockingQueue<Data> msgQueue) {
        Data d=new Data();
        d.setStatus("running");
        FinancialData fd=new FinancialData();
        fd.setName("Markit");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        fd.setAsOf(dateFormat.format(date));
        Payload p=new Payload();
        p.setPrice(400.0);
        fd.setPayload(p);
        d.setFinancialData(fd);
        msgQueue.add(d);

    }


    @Override
    public void startProducer() throws InterruptedException {
        Data d=new Data();
        d.setStatus("start");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        FinancialData fd=new FinancialData();
        fd.setAsOf(dateFormat.format(date));
        d.setFinancialData(fd);
        kafkaTemplate2.send(new ProducerRecord<String, Data>("financial_instruments", d));
        flag="running";
        msgQueue.clear();
        runProducer();
    }

    public void stopProducer(){
        Data d=new Data();
        d.setStatus("close");
        flag="close";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        FinancialData fd=new FinancialData();
        fd.setAsOf(dateFormat.format(date));
        d.setFinancialData(fd);
        msgQueue.clear();
        kafkaTemplate2.send(new ProducerRecord<String, Data>("financial_instruments", d));
    }

    public void interruptProducer(){
        Data d=new Data();
        d.setStatus("error");
        flag="error";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        FinancialData fd=new FinancialData();
        fd.setAsOf(dateFormat.format(date));
        d.setFinancialData(fd);
        msgQueue.clear();
        kafkaTemplate2.send(new ProducerRecord<String, Data>("financial_instruments", d));
    }

}



