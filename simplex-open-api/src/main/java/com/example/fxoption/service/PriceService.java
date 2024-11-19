// PriceService.java
package com.example.fxoption.service;

import com.example.fxoption.entity.Price;
import com.example.fxoption.entity.PriceRequestEntity;
import com.example.fxoption.repository.PriceRepository;
import com.example.fxoption.repository.PriceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceRequestRepository priceRequestRepository;

    public Price savePrice(Price price, PriceRequestEntity priceRequestEntity) {
        if (price.getPriceId() == null || price.getPriceId().isEmpty()) {
            price.setPriceId(UUID.randomUUID().toString());
        }
        priceRequestRepository.save(priceRequestEntity);
        return priceRepository.save(price);
    }
}