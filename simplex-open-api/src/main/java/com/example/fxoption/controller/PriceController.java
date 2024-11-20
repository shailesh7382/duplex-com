// PriceController.java
package com.example.fxoption.controller;

import com.example.fxoption.api.PriceApi;
import com.example.fxoption.entity.PriceRequestEntityBuilder;
import com.example.fxoption.entity.Price;
import com.example.fxoption.entity.PriceRequestEntity;
import com.example.fxoption.model.PriceRequest;
import com.example.fxoption.model.PriceResponse;
import com.example.fxoption.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PriceController implements PriceApi {
    @Autowired
    private PriceService priceService;


    public ResponseEntity<PriceResponse> getPrice(@RequestBody PriceRequest priceRequest) {
        PriceRequestEntity priceRequestEntity = PriceRequestEntityBuilder.from(priceRequest).build();

        Price price = new Price();
        price.setPriceId("1");
        price.setPriceRequestId(priceRequestEntity.getPriceRequestId());
        price = priceService.savePrice(price, priceRequestEntity);
        price.setPrice(100.0); // Set the price value
        price.setStatus("PRICE_SUCCESS"); // Set the status value
        return ResponseEntity.ok(price.toPriceResponse());
    }
}