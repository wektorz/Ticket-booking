package com.example.demo.model;

import java.util.Date;

public class ReservationResponseModel {
    Double price;
    Date expireTime;

    public ReservationResponseModel() {
    }

    public ReservationResponseModel(Double price, Date expireTime) {
        this.price = price;
        this.expireTime = expireTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "ReservationResponseModel{" +
                "price=" + price +
                ", expireTime=" + expireTime +
                '}';
    }
}
