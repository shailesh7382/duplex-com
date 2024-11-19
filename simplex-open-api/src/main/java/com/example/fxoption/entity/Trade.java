// Trade.java
package com.example.fxoption.entity;

import com.example.fxoption.model.TradeResponse;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trade {
    @Id
    private String tradeId;
    private int quantity;
    private double price;
    private String status;
    private String reason;

    // Getters and setters
    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public TradeResponse toTradeResponse() {
        TradeResponse tradeResponse = new TradeResponse();
        tradeResponse.setTradeId(this.tradeId);
        tradeResponse.setQuantity(this.quantity);
        tradeResponse.setPrice(this.price);
        tradeResponse.setStatus(TradeResponse.StatusEnum.fromValue(this.status));
        tradeResponse.setReason(TradeResponse.ReasonEnum.fromValue(this.reason));
        return tradeResponse;
    }
}