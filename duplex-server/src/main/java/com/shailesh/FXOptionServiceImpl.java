package com.shailesh;

import com.example.shailesh.fxoption.*;
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

@Service
public class FXOptionServiceImpl extends FXOptionServiceGrpc.FXOptionServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(FXOptionServiceImpl.class);

    @Override
    public void getPrice(PriceRequest request, StreamObserver<PriceResponse> responseObserver) {
        logger.info("Received PriceRequest: {}", request);

        // Simulate price response
        PriceResponse priceResponse = PriceResponse.newBuilder()
                .setPriceId("price-123")
                .setPriceRequestId(request.getPriceRequestId())
                .setPrice(1.25)
                .setStatus(PriceRespStatus.PRICE_SUCCESS)
                .build();

        // Send the response
        responseObserver.onNext(priceResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void tradeOption(TradeRequest request, StreamObserver<TradeResponse> responseObserver) {
        logger.info("Received TradeRequest: {}", request);

        // Simulate trade response
        TradeResponse tradeResponse = TradeResponse.newBuilder()
                .setTradeId(request.getTradeId())
                .setQuantity(request.getQuantity())
                .setPrice(request.getPrice())
                .setStatus(TradeState.TRADE_SUCCESS)
                .setReason(TradeStatusReason.NONE)
                .build();

        // Send the response
        responseObserver.onNext(tradeResponse);
        responseObserver.onCompleted();
    }
}
