package com.shailesh.service;

import com.example.shailesh.fxoption.*;
import com.example.shailesh.fxoption.Fxoption.BuySell;
import com.example.shailesh.fxoption.Fxoption.Date;
import com.example.shailesh.fxoption.Fxoption.FXOptionDetails;
import com.example.shailesh.fxoption.Fxoption.FXOptionType;
import com.example.shailesh.fxoption.Fxoption.PriceRequest;
import com.example.shailesh.fxoption.Fxoption.PriceResponse;
import com.example.shailesh.fxoption.Fxoption.TradeRequest;
import com.example.shailesh.fxoption.Fxoption.TradeResponse;

import io.grpc.stub.StreamObserver;
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
                callTradeOption(priceRequest, priceResponse);
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

    private void callTradeOption(PriceRequest priceRequest, PriceResponse priceResponse) {
        TradeRequest tradeRequest = TradeRequest.newBuilder()
                .setTradeId("tr-789")
                .setPriceId(priceResponse.getPriceId())
                .setCif("cif-456")
                .setOption(priceRequest.getOption())
                .setQuantity(1000)
                .setPrice(priceResponse.getPrice())
                .setDirection(BuySell.BUY)
                .build();

        TradeResponse tradeResponse = fxOptionServiceBlockingStub.tradeOption(tradeRequest);
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
                        .setExpirationDate(Date.newBuilder().setYear(2023).setMonth(12).setDay(31).build())
                        .setOptionType(FXOptionType.CALL)
                        .setCcyPair("EUR/USD")
                        .setQuantity(1000)
                        .build())
                .build();
    }
}
