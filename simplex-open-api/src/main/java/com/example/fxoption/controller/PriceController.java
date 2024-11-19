// PriceController.java
package com.example.fxoption.controller;

import com.example.fxoption.entity.Price;
import com.example.fxoption.entity.PriceRequest;
import com.example.fxoption.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price")
public class PriceController {
    @Autowired
    private PriceService priceService;

    @PostMapping("/getPrice")
    public ResponseEntity<Price> getPrice(@RequestBody PriceRequest priceRequest) {
        Price price = new Price();
        price.setPriceId("1");
        price.setPriceRequestId(priceRequest.getPriceRequestId());
        price = priceService.savePrice(price, priceRequest);
        price.setPrice(100.0); // Set the price value
        price.setStatus("PRICE_SUCCESS"); // Set the status value
        return ResponseEntity.ok(price);
    }
}