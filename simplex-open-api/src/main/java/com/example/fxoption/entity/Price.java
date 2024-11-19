// Price.java
package com.example.fxoption.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Price {
    @Id
    private String priceId;
    private String priceRequestId;
    private double price;
    private String status;

    // Getters and setters
    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public String getPriceRequestId() {
        return priceRequestId;
    }

    public void setPriceRequestId(String priceRequestId) {
        this.priceRequestId = priceRequestId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}