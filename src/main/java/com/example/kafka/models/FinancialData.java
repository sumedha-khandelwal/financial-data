package com.example.kafka.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "financial_data")
public class FinancialData {


    private String name;
    private String asOf;
    private Payload payload;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAsOf() {
        return asOf;
    }

    public void setAsOf(String asOf) {
        this.asOf = asOf;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "FinancialData{" +
                "name='" + name + '\'' +
                ", asOf='" + asOf + '\'' +
                ", payload=" + payload +
                '}';
    }
}
