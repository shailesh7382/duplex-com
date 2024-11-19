// TradeRequestEntityBuilder.java
package com.example.fxoption.entity;

import com.example.fxoption.model.TradeRequest;

public class TradeRequestEntityBuilder {
    private String tradeId;
    private String priceId;
    private String cif;
    private FXOptionDetailsEntity option;
    private int quantity;
    private double price;
    private String direction;

    public TradeRequestEntityBuilder tradeId(String tradeId) {
        this.tradeId = tradeId;
        return this;
    }

    public TradeRequestEntityBuilder priceId(String priceId) {
        this.priceId = priceId;
        return this;
    }

    public TradeRequestEntityBuilder cif(String cif) {
        this.cif = cif;
        return this;
    }

    public TradeRequestEntityBuilder option(FXOptionDetailsEntity option) {
        this.option = option;
        return this;
    }

    public TradeRequestEntityBuilder quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public TradeRequestEntityBuilder price(double price) {
        this.price = price;
        return this;
    }

    public TradeRequestEntityBuilder direction(String direction) {
        this.direction = direction;
        return this;
    }

    public TradeRequestEntity build() {
        TradeRequestEntity tradeRequestEntity = new TradeRequestEntity();
        tradeRequestEntity.setTradeId(this.tradeId);
        tradeRequestEntity.setPriceId(this.priceId);
        tradeRequestEntity.setCif(this.cif);
        tradeRequestEntity.setOption(this.option);
        tradeRequestEntity.setQuantity(this.quantity);
        tradeRequestEntity.setPrice(this.price);
        tradeRequestEntity.setDirection(this.direction);
        return tradeRequestEntity;
    }

    public static TradeRequestEntityBuilder from(TradeRequest tradeRequest) {
        return new TradeRequestEntityBuilder()
                .tradeId(tradeRequest.getTradeId())
                .priceId(tradeRequest.getPriceId())
                .cif(tradeRequest.getCif())
                .option(FXOptionDetailsEntityBuilder.from(tradeRequest.getOption()).build())
                .quantity(tradeRequest.getQuantity())
                .price(tradeRequest.getPrice())
                .direction(tradeRequest.getDirection().getValue());
    }
}