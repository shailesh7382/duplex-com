package com.shailesh.service;

import com.example.shailesh.fxoption.*;
import com.example.shailesh.fxoption.Fxoption.HeartbeatRequest;
import com.example.shailesh.fxoption.Fxoption.HeartbeatResponse;
import com.example.shailesh.fxoption.Fxoption.PriceRequest;
import com.example.shailesh.fxoption.Fxoption.PriceRespStatus;
import com.example.shailesh.fxoption.Fxoption.PriceResponse;
import com.example.shailesh.fxoption.Fxoption.TradeRequest;
import com.example.shailesh.fxoption.Fxoption.TradeResponse;
import com.example.shailesh.fxoption.Fxoption.TradeState;
import com.example.shailesh.fxoption.Fxoption.TradeStatusReason;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Service
public class FXOptionServiceImpl extends FXOptionServiceGrpc.FXOptionServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(FXOptionServiceImpl.class);

    // Cache to store prices
    private final ConcurrentMap<String, Double> priceCache = new ConcurrentHashMap<>();

    @Override
    public void getPrice(PriceRequest request, StreamObserver<PriceResponse> responseObserver) {
        logger.info("Received PriceRequest: {}", request);

        // Simulate sending 5 price responses
        for (int i = 1; i <= 5; i++) {
            double price = 1.25 + i * 0.01; // Increment price for each response
            PriceResponse priceResponse = PriceResponse.newBuilder()
                    .setPriceId("price-" + i)
                    .setPriceRequestId(request.getPriceRequestId())
                    .setPrice(price)
                    .setStatus(PriceRespStatus.PRICE_SUCCESS)
                    .build();

            // Cache the price
            priceCache.put(priceResponse.getPriceId(), price);

            // Log and send the response
            logger.info("Sending PriceResponse: {}", priceResponse);
            responseObserver.onNext(priceResponse);

            // Simulate delay between responses
            try {
                Thread.sleep(1000); // 1 second delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Thread interrupted", e);
            }
        }

        // Complete the response stream
        responseObserver.onCompleted();
    }

    @Override
    public void tradeOption(TradeRequest request, StreamObserver<TradeResponse> responseObserver) {
        logger.info("Received TradeRequest: {}", request);

        // Validate the trade request using the cached prices
        Double cachedPrice = priceCache.get(request.getPriceId());
        TradeState tradeState;
        TradeStatusReason tradeStatusReason;

        if (cachedPrice != null && cachedPrice.equals(request.getPrice())) {
            tradeState = TradeState.TRADE_SUCCESS;
            tradeStatusReason = TradeStatusReason.NONE;
            logger.info("Trade validated successfully");
        } else {
            tradeState = TradeState.TRADE_FAILURE;
            tradeStatusReason = TradeStatusReason.INVALID_QUANTITY; // Use appropriate reason
            logger.warn("Trade validation failed");
        }

        // Simulate trade response
        TradeResponse tradeResponse = TradeResponse.newBuilder()
                .setTradeId(request.getTradeId())
                .setQuantity(request.getQuantity())
                .setPrice(request.getPrice())
                .setStatus(tradeState)
                .setReason(tradeStatusReason)
                .build();

        // Log and send the response
        logger.info("Sending TradeResponse: {}", tradeResponse);
        responseObserver.onNext(tradeResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void heartbeat(HeartbeatRequest request, StreamObserver<HeartbeatResponse> responseObserver) {
        logger.info("Received HeartbeatRequest: {} at {}", request.getMessage(), request.getTimestamp());
        HeartbeatResponse response = HeartbeatResponse.newBuilder()
                .setMessage("pong")
                .setTimestamp(System.currentTimeMillis())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
