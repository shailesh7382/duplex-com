// PriceRequest.java
package com.example.fxoption.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Embedded;

@Entity
public class PriceRequest {
    @Id
    private String priceRequestId;
    private String cif;
    @Embedded
    private FXOptionDetails option;

    // Getters and setters
    public String getPriceRequestId() {
        return priceRequestId;
    }

    public void setPriceRequestId(String priceRequestId) {
        this.priceRequestId = priceRequestId;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public FXOptionDetails getOption() {
        return option;
    }

    public void setOption(FXOptionDetails option) {
        this.option = option;
    }
}