package com.example.kafka.models;

import org.springframework.data.mongodb.core.mapping.Document;


public class Data {

    private String status;
    private FinancialData financialData;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FinancialData getFinancialData() {
        return financialData;
    }

    public void setFinancialData(FinancialData financialData) {
        this.financialData = financialData;
    }

    @Override
    public String toString() {
        return "Data{" +
                "status='" + status + '\'' +
                ", financialData=" + financialData +
                '}';
    }
}
