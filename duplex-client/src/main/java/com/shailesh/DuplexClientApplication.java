package com.shailesh;

import com.example.shailesh.fxoption.*;
import com.example.shailesh.fxoption.Fxoption.BuySell;
import com.example.shailesh.fxoption.Fxoption.Date;
import com.example.shailesh.fxoption.Fxoption.FXOptionDetails;
import com.example.shailesh.fxoption.Fxoption.FXOptionType;
import com.example.shailesh.fxoption.Fxoption.PriceRequest;
import com.example.shailesh.fxoption.Fxoption.PriceResponse;
import com.example.shailesh.fxoption.Fxoption.TradeRequest;
import com.example.shailesh.fxoption.Fxoption.TradeResponse;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DuplexClientApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DuplexClientApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(DuplexClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                                                      .usePlaintext()
                                                      .build();

        FXOptionServiceGrpc.FXOptionServiceStub fxOptionServiceStub = FXOptionServiceGrpc.newStub(channel);

        // Create a price request
        PriceRequest priceRequest = PriceRequest.newBuilder()
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

        // Call the GetPrice RPC and handle the streaming response
        fxOptionServiceStub.getPrice(priceRequest, new StreamObserver<PriceResponse>() {
            @Override
            public void onNext(PriceResponse priceResponse) {
                logger.info("Price Response: {}", priceResponse);

                // Create a trade request
                TradeRequest tradeRequest = TradeRequest.newBuilder()
                        .setTradeId("tr-789")
                        .setPriceId(priceResponse.getPriceId())
                        .setCif("cif-456")
                        .setOption(priceRequest.getOption())
                        .setQuantity(1000)
                        .setPrice(priceResponse.getPrice())
                        .setDirection(BuySell.BUY)
                        .build();

                // Call the TradeOption RPC
                FXOptionServiceGrpc.FXOptionServiceBlockingStub fxOptionServiceBlockingStub = FXOptionServiceGrpc.newBlockingStub(channel);
                TradeResponse tradeResponse = fxOptionServiceBlockingStub.tradeOption(tradeRequest);
                logger.info("Trade Response: {}", tradeResponse);
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

        // Keep the application running to receive the streaming responses
        Thread.sleep(10000);
        channel.shutdown();
    }
}