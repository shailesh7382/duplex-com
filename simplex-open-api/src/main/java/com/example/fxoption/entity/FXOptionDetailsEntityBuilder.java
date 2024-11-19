// FXOptionDetailsEntityBuilder.java
package com.example.fxoption.entity;

import com.example.fxoption.model.FXOptionDetails;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class FXOptionDetailsEntityBuilder {
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

    public FXOptionDetailsEntityBuilder underlyingCurrency(String underlyingCurrency) {
        this.underlyingCurrency = underlyingCurrency;
        return this;
    }

    public FXOptionDetailsEntityBuilder strikeCurrency(String strikeCurrency) {
        this.strikeCurrency = strikeCurrency;
        return this;
    }

    public FXOptionDetailsEntityBuilder strikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
        return this;
    }

    public FXOptionDetailsEntityBuilder expirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
        return this;
    }

    public FXOptionDetailsEntityBuilder optionType(String optionType) {
        this.optionType = optionType;
        return this;
    }

    public FXOptionDetailsEntityBuilder ccyPair(String ccyPair) {
        this.ccyPair = ccyPair;
        return this;
    }

    public FXOptionDetailsEntityBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public FXOptionDetailsEntityBuilder premium(double premium) {
        this.premium = premium;
        return this;
    }

    public FXOptionDetailsEntityBuilder tradeDate(LocalDate tradeDate) {
        this.tradeDate = tradeDate;
        return this;
    }

    public FXOptionDetailsEntityBuilder settlementDate(LocalDate settlementDate) {
        this.settlementDate = settlementDate;
        return this;
    }

    public FXOptionDetailsEntityBuilder counterparty(String counterparty) {
        this.counterparty = counterparty;
        return this;
    }

    public FXOptionDetailsEntityBuilder comments(String comments) {
        this.comments = comments;
        return this;
    }

    public FXOptionDetailsEntityBuilder barrierType(String barrierType) {
        this.barrierType = barrierType;
        return this;
    }

    public FXOptionDetailsEntityBuilder barrierLevel(double barrierLevel) {
        this.barrierLevel = barrierLevel;
        return this;
    }

    public FXOptionDetailsEntityBuilder lowerBarrierLevel(double lowerBarrierLevel) {
        this.lowerBarrierLevel = lowerBarrierLevel;
        return this;
    }

    public FXOptionDetailsEntityBuilder upperBarrierLevel(double upperBarrierLevel) {
        this.upperBarrierLevel = upperBarrierLevel;
        return this;
    }

    public FXOptionDetailsEntity build() {
        FXOptionDetailsEntity fxOptionDetailsEntity = new FXOptionDetailsEntity();
        fxOptionDetailsEntity.setUnderlyingCurrency(this.underlyingCurrency);
        fxOptionDetailsEntity.setStrikeCurrency(this.strikeCurrency);
        fxOptionDetailsEntity.setStrikePrice(this.strikePrice);
        fxOptionDetailsEntity.setExpirationDate(this.expirationDate);
        fxOptionDetailsEntity.setOptionType(this.optionType);
        fxOptionDetailsEntity.setCcyPair(this.ccyPair);
        fxOptionDetailsEntity.setQuantity(this.quantity);
        fxOptionDetailsEntity.setPremium(this.premium);
        fxOptionDetailsEntity.setTradeDate(this.tradeDate);
        fxOptionDetailsEntity.setSettlementDate(this.settlementDate);
        fxOptionDetailsEntity.setCounterparty(this.counterparty);
        fxOptionDetailsEntity.setComments(this.comments);
        fxOptionDetailsEntity.setBarrierType(this.barrierType);
        fxOptionDetailsEntity.setBarrierLevel(this.barrierLevel);
        fxOptionDetailsEntity.setLowerBarrierLevel(this.lowerBarrierLevel);
        fxOptionDetailsEntity.setUpperBarrierLevel(this.upperBarrierLevel);
        return fxOptionDetailsEntity;
    }

    public static FXOptionDetailsEntityBuilder from(FXOptionDetails fxOptionDetails) {
        LocalDate expirationDate = fxOptionDetails.getExpirationDate();
        LocalDate tradeDate = fxOptionDetails.getTradeDate();
        LocalDate settlementDate = fxOptionDetails.getSettlementDate();

        return new FXOptionDetailsEntityBuilder()
                .underlyingCurrency(fxOptionDetails.getUnderlyingCurrency())
                .strikeCurrency(fxOptionDetails.getStrikeCurrency())
                .strikePrice(fxOptionDetails.getStrikePrice())
                .expirationDate(expirationDate)
                .optionType(fxOptionDetails.getOptionType().getValue())
                .ccyPair(fxOptionDetails.getCcyPair())
                .quantity(fxOptionDetails.getQuantity())
                .premium(fxOptionDetails.getPremium())
                .tradeDate(tradeDate)
                .settlementDate(settlementDate)
                .counterparty(fxOptionDetails.getCounterparty())
                .comments(fxOptionDetails.getComments())
                .barrierType(fxOptionDetails.getBarrierType().getValue())
                .barrierLevel(fxOptionDetails.getBarrierLevel())
                .lowerBarrierLevel(fxOptionDetails.getLowerBarrierLevel())
                .upperBarrierLevel(fxOptionDetails.getUpperBarrierLevel());
    }

    private static LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}