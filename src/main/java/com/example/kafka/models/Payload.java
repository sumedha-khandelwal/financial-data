package com.example.kafka.models;

public class Payload {

    private Double price;
    private String addInfo;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "price=" + price +
                ", addInfo='" + addInfo + '\'' +
                '}';
    }
}
