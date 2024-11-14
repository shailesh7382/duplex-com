package fxoption.controller;

import com.example.shailesh.fxoption.FxOptionProto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trade")
public class TradeController {

    @PostMapping("/tradeOption")
    public TradeResponse tradeOption(@RequestBody TradeRequest request) {
        // Simulate trade response
        TradeState tradeState = TradeState.TRADE_SUCCESS;
        TradeStatusReason tradeStatusReason = TradeStatusReason.NONE;

        return TradeResponse.newBuilder()
                .setTradeId(request.getTradeId())
                .setQuantity(request.getQuantity())
                .setPrice(request.getPrice())
                .setStatus(tradeState)
                .setReason(tradeStatusReason)
                .build();
    }
}