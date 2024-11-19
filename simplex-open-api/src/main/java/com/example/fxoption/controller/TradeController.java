// TradeController.java
package com.example.fxoption.controller;

import com.example.fxoption.entity.Trade;
import com.example.fxoption.entity.TradeRequest;
import com.example.fxoption.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
public class TradeController {
    @Autowired
    private TradeService tradeService;

    @PostMapping("/tradeOption")
    public ResponseEntity<Trade> tradeOption(@RequestBody TradeRequest tradeRequest) {
        Trade trade = new Trade();
        trade.setTradeId(tradeRequest.getTradeId());
        trade.setQuantity(tradeRequest.getQuantity());
        trade.setPrice(tradeRequest.getPrice());
        trade.setStatus("TRADE_SUCCESS");
        trade.setReason("NONE");
        trade = tradeService.saveTrade(trade, tradeRequest);
        return ResponseEntity.ok(trade);
    }
}