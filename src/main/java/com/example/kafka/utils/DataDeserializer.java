package com.example.kafka.utils;

import com.example.kafka.models.Data;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class DataDeserializer implements Deserializer<Data> {

    @Override public void close() {

    }

    @Override public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    @Override
    public Data deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        Data user = null;
        try {
            user = mapper.readValue(arg1, Data.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

}
