package com.example.kafka.models;

public class Payload {

    private double price;
    private String addInfo;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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
