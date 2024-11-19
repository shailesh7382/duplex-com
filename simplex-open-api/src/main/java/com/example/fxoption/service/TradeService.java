// TradeService.java
package com.example.fxoption.service;

import com.example.fxoption.entity.Trade;
import com.example.fxoption.entity.TradeRequest;
import com.example.fxoption.repository.TradeRepository;
import com.example.fxoption.repository.TradeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TradeService {
    @Autowired
    private TradeRepository tradeRepository;

    @Autowired
    private TradeRequestRepository tradeRequestRepository;

    public Trade saveTrade(Trade trade, TradeRequest tradeRequest) {
        if (trade.getTradeId() == null || trade.getTradeId().isEmpty()) {
            trade.setTradeId(UUID.randomUUID().toString());
        }
        tradeRequestRepository.save(tradeRequest);
        return tradeRepository.save(trade);
    }
}