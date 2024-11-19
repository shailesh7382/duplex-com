// TradeController.java
package com.example.fxoption.controller;

import com.example.fxoption.api.TradeApi;
import com.example.fxoption.entity.TradeRequestEntityBuilder;
import com.example.fxoption.entity.Trade;
import com.example.fxoption.entity.TradeRequestEntity;
import com.example.fxoption.model.TradeRequest;
import com.example.fxoption.model.TradeResponse;
import com.example.fxoption.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TradeController implements TradeApi {
    @Autowired
    private TradeService tradeService;

    public ResponseEntity<TradeResponse> tradeOption(@RequestBody TradeRequest tradeRequest) {
        TradeRequestEntity tradeRequestEntity = TradeRequestEntityBuilder.from(tradeRequest).build();

        Trade trade = new Trade();
        trade.setTradeId(tradeRequestEntity.getTradeId());
        trade.setQuantity(tradeRequestEntity.getQuantity());
        trade.setPrice(tradeRequestEntity.getPrice());
        trade.setStatus("TRADE_SUCCESS");
        trade.setReason("NONE");
        trade = tradeService.saveTrade(trade, tradeRequestEntity);
        return ResponseEntity.ok(trade.toTradeResponse());
    }
}