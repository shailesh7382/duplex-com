package com.shailesh.service;

import com.example.shailesh.fxoption.*;
import com.example.shailesh.fxoption.FxOptionProto.BuySell;
import com.example.shailesh.fxoption.FxOptionProto.FXOptionDetails;
import com.example.shailesh.fxoption.FxOptionProto.FXOptionType;
import com.example.shailesh.fxoption.FxOptionProto.HeartbeatRequest;
import com.example.shailesh.fxoption.FxOptionProto.HeartbeatResponse;
import com.example.shailesh.fxoption.FxOptionProto.PriceRequest;
import com.example.shailesh.fxoption.FxOptionProto.PriceResponse;
import com.example.shailesh.fxoption.FxOptionProto.TradeRequest;
import com.example.shailesh.fxoption.FxOptionProto.TradeResponse;
import com.example.shailesh.fxoption.FxOptionProto.TradeState;
import com.example.shailesh.fxoption.FxOptionProto.TradeStatusReason;

import io.grpc.stub.StreamObserver;

import java.sql.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FXOptionClientService {

    private static final Logger logger = LoggerFactory.getLogger(FXOptionClientService.class);

    @Autowired
    private FXOptionServiceGrpc.FXOptionServiceStub fxOptionServiceStub;

    @Autowired
    private FXOptionServiceGrpc.FXOptionServiceBlockingStub fxOptionServiceBlockingStub;

    public void callGetPrice(PriceRequest priceRequest) {
        fxOptionServiceStub.getPrice(priceRequest, new StreamObserver<PriceResponse>() {
            @Override
            public void onNext(PriceResponse priceResponse) {
                logger.info("Price Response: {}", priceResponse);
                // Simulate different scenarios based on the price response
                if (priceResponse.getPrice() > 1.26) {
                    logger.info("Simulating a successful trade scenario.");
                    callTradeOption(priceRequest, priceResponse, true);
                } else {
                    logger.info("Simulating a failed trade scenario.");
                    callTradeOption(priceRequest, priceResponse, false);
                }
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error: {}", t.getMessage());
            }

            @Override
            public void onCompleted() {
                logger.info("Stream completed");
            }
        });
    }

    private void callTradeOption(PriceRequest priceRequest, PriceResponse priceResponse, boolean isSuccess) {
        TradeRequest tradeRequest = TradeRequest.newBuilder()
                .setTradeId("tr-789")
                .setPriceId(priceResponse.getPriceId())
                .setCif("cif-456")
                .setOption(priceRequest.getOption())
                .setQuantity(1000)
                .setPrice(priceResponse.getPrice())
                .setDirection(BuySell.BUY)
                .build();

        TradeResponse tradeResponse;
        if (isSuccess) {
            tradeResponse = TradeResponse.newBuilder()
                    .setTradeId(tradeRequest.getTradeId())
                    .setQuantity(tradeRequest.getQuantity())
                    .setPrice(tradeRequest.getPrice())
                    .setStatus(TradeState.TRADE_SUCCESS)
                    .setReason(TradeStatusReason.NONE)
                    .build();
        } else {
            tradeResponse = TradeResponse.newBuilder()
                    .setTradeId(tradeRequest.getTradeId())
                    .setQuantity(tradeRequest.getQuantity())
                    .setPrice(tradeRequest.getPrice())
                    .setStatus(TradeState.TRADE_FAILURE)
                    .setReason(TradeStatusReason.INSUFFICIENT_FUNDS)
                    .build();
        }

        logger.info("Trade Response: {}", tradeResponse);
    }

    public PriceRequest createPriceRequest() {
        return PriceRequest.newBuilder()
                .setPriceRequestId("pr-123")
                .setCif("cif-456")
                .setOption(FXOptionDetails.newBuilder()
                        .setUnderlyingCurrency("USD")
                        .setStrikeCurrency("EUR")
                        .setStrikePrice(1.2)
                        .setExpirationDate(com.example.shailesh.fxoption.FxOptionProto.Date.newBuilder().setYear(2023).setMonth(12).setDay(31).build())
                        .setOptionType(FXOptionType.CALL)
                        .setCcyPair("EUR/USD")
                        .setQuantity(1000)
                        .build())
                .build();
    }

     public void sendHeartbeat() {
        HeartbeatRequest request = HeartbeatRequest.newBuilder()
                .setMessage("ping")
                .setTimestamp(System.currentTimeMillis())
                .build();
        fxOptionServiceStub.heartbeat(request, new StreamObserver<HeartbeatResponse>() {
            @Override
            public void onNext(HeartbeatResponse response) {
                logger.info("Received HeartbeatResponse: {} at {}", response.getMessage(), response.getTimestamp());
            }

            @Override
            public void onError(Throwable t) {
                logger.error("Error: {}", t.getMessage());
            }

            @Override
            public void onCompleted() {
                logger.info("Heartbeat completed");
            }
        });
    }
}
