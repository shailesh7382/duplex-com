// PriceRequestEntityBuilder.java
package com.example.fxoption.entity;

import com.example.fxoption.model.PriceRequest;

public class PriceRequestEntityBuilder {
    private String priceRequestId;
    private String cif;
    private FXOptionDetailsEntity option;

    public PriceRequestEntityBuilder priceRequestId(String priceRequestId) {
        this.priceRequestId = priceRequestId;
        return this;
    }

    public PriceRequestEntityBuilder cif(String cif) {
        this.cif = cif;
        return this;
    }

    public PriceRequestEntityBuilder option(FXOptionDetailsEntity option) {
        this.option = option;
        return this;
    }

    public PriceRequestEntity build() {
        PriceRequestEntity priceRequestEntity = new PriceRequestEntity();
        priceRequestEntity.setPriceRequestId(this.priceRequestId);
        priceRequestEntity.setCif(this.cif);
        priceRequestEntity.setOption(this.option);
        return priceRequestEntity;
    }

    public static PriceRequestEntityBuilder from(PriceRequest priceRequest) {
        return new PriceRequestEntityBuilder()
                .priceRequestId(priceRequest.getPriceRequestId())
                .cif(priceRequest.getCif())
                .option(FXOptionDetailsEntityBuilder.from(priceRequest.getOption()).build());
    }
}