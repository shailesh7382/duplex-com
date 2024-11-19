// FXOptionDetailsEntity.java
package com.example.fxoption.entity;

import java.time.LocalDate;

public class FXOptionDetailsEntity {
    private String underlyingCurrency;
    private String strikeCurrency;
    private double strikePrice;
    private LocalDate expirationDate;
    private String optionType;
    private String ccyPair;
    private int quantity;
    private double premium;
    private LocalDate tradeDate;
    private LocalDate settlementDate;
    private String counterparty;
    private String comments;
    private String barrierType;
    private double barrierLevel;
    private double lowerBarrierLevel;
    private double upperBarrierLevel;

    // Getters and setters

    public String getUnderlyingCurrency() {
        return underlyingCurrency;
    }

    public void setUnderlyingCurrency(String underlyingCurrency) {
        this.underlyingCurrency = underlyingCurrency;
    }

    public String getStrikeCurrency() {
        return strikeCurrency;
    }

    public void setStrikeCurrency(String strikeCurrency) {
        this.strikeCurrency = strikeCurrency;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getOptionType() {
        return optionType;
    }

    public void setOptionType(String optionType) {
        this.optionType = optionType;
    }

    public String getCcyPair() {
        return ccyPair;
    }

    public void setCcyPair(String ccyPair) {
        this.ccyPair = ccyPair;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public LocalDate getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getCounterparty() {
        return counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.counterparty = counterparty;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getBarrierType() {
        return barrierType;
    }

    public void setBarrierType(String barrierType) {
        this.barrierType = barrierType;
    }

    public double getBarrierLevel() {
        return barrierLevel;
    }

    public void setBarrierLevel(double barrierLevel) {
        this.barrierLevel = barrierLevel;
    }

    public double getLowerBarrierLevel() {
        return lowerBarrierLevel;
    }

    public void setLowerBarrierLevel(double lowerBarrierLevel) {
        this.lowerBarrierLevel = lowerBarrierLevel;
    }

    public double getUpperBarrierLevel() {
        return upperBarrierLevel;
    }

    public void setUpperBarrierLevel(double upperBarrierLevel) {
        this.upperBarrierLevel = upperBarrierLevel;
    }
}