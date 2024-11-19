// FXOptionDetails.java
package com.example.fxoption.entity;

import javax.persistence.Embeddable;

@Embeddable
public class FXOptionDetails {
    private String underlyingCurrency;
    private String strikeCurrency;
    private double strikePrice;
    private int expirationYear;
    private int expirationMonth;
    private int expirationDay;
    private String optionType;
    private String ccyPair;
    private int quantity;
    private double premium;
    private String tradeDate;
    private String settlementDate;
    private String counterparty;
    private String comments;
    private String barrierType;
    private double barrierLevel;
    private double lowerBarrierLevel;
    private double upperBarrierLevel;

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

    public int getExpirationYear() {
        return expirationYear;
    }

    public void setExpirationYear(int expirationYear) {
        this.expirationYear = expirationYear;
    }

    public int getExpirationMonth() {
        return expirationMonth;
    }

    public void setExpirationMonth(int expirationMonth) {
        this.expirationMonth = expirationMonth;
    }

    public int getExpirationDay() {
        return expirationDay;
    }

    public void setExpirationDay(int expirationDay) {
        this.expirationDay = expirationDay;
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

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
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