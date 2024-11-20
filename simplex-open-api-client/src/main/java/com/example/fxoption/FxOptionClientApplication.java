package com.example.fxoption;

import com.example.fxoption.client.FxOptionClient;
import com.example.fxoption.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@SpringBootApplication
public class FxOptionClientApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(FxOptionClientApplication.class);

    @Autowired
    private FxOptionClient fxOptionClient;

    @Autowired
    private FxOptionClient webFxOptionClient;

    public static void main(String[] args) {
        SpringApplication.run(FxOptionClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        PriceRequest priceRequest = createPriceRequest();
        logPriceResponse(fxOptionClient.getPrice(priceRequest));
        logPriceResponse(webFxOptionClient.getPrice(priceRequest));

        TradeRequest tradeRequest = createTradeRequest();
        logTradeResponse(fxOptionClient.tradeOption(tradeRequest));
        logTradeResponse(webFxOptionClient.tradeOption(tradeRequest));
    }

    private PriceRequest createPriceRequest() {
        PriceRequest priceRequest = new PriceRequest();
        priceRequest.setPriceRequestId("PR12345");
        priceRequest.setCif("CIF67890");

        FXOptionDetails priceOptionDetails = new FXOptionDetails();
        priceOptionDetails.setUnderlyingCurrency("USD");
        priceOptionDetails.setStrikeCurrency("EUR");
        priceOptionDetails.setStrikePrice(1.15);
        priceOptionDetails.setExpirationDate(LocalDate.of(2023, 12, 1));
        priceOptionDetails.setOptionType(FXOptionDetails.OptionTypeEnum.CALL);
        priceOptionDetails.setCcyPair("EUR/USD");
        priceOptionDetails.setQuantity(1000);
        priceOptionDetails.setPremium(1.20);
        priceOptionDetails.setTradeDate(LocalDate.of(2023, 10, 1));
        priceOptionDetails.setSettlementDate(LocalDate.of(2023, 12, 2));
        priceOptionDetails.setCounterparty("CounterpartyName");
        priceOptionDetails.setComments("Sample comment");
        priceOptionDetails.setBarrierType(FXOptionDetails.BarrierTypeEnum.UP_AND_IN);
        priceOptionDetails.setBarrierLevel(1.10);
        priceOptionDetails.setLowerBarrierLevel(1.05);
        priceOptionDetails.setUpperBarrierLevel(1.20);

        priceRequest.setOption(priceOptionDetails);
        return priceRequest;
    }

    private TradeRequest createTradeRequest() {
        TradeRequest tradeRequest = new TradeRequest();
        tradeRequest.setTradeId("TR12345");
        tradeRequest.setCif("CIF67890");
        tradeRequest.setQuantity(1000);
        tradeRequest.setPrice(1.20);
        tradeRequest.setDirection(TradeRequest.DirectionEnum.BUY);

        FXOptionDetails tradeOptionDetails = new FXOptionDetails();
        tradeOptionDetails.setUnderlyingCurrency("USD");
        tradeOptionDetails.setStrikeCurrency("EUR");
        tradeOptionDetails.setStrikePrice(1.15);
        tradeOptionDetails.setExpirationDate(LocalDate.of(2023, 12, 1));
        tradeOptionDetails.setOptionType(FXOptionDetails.OptionTypeEnum.CALL);
        tradeOptionDetails.setCcyPair("EUR/USD");
        tradeOptionDetails.setQuantity(1000);
        tradeOptionDetails.setPremium(1.20);
        tradeOptionDetails.setTradeDate(LocalDate.of(2023, 10, 1));
        tradeOptionDetails.setSettlementDate(LocalDate.of(2023, 12, 2));
        tradeOptionDetails.setCounterparty("CounterpartyName");
        tradeOptionDetails.setComments("Sample comment");
        tradeOptionDetails.setBarrierType(FXOptionDetails.BarrierTypeEnum.UP_AND_IN);
        tradeOptionDetails.setBarrierLevel(1.10);
        tradeOptionDetails.setLowerBarrierLevel(1.05);
        tradeOptionDetails.setUpperBarrierLevel(1.20);

        tradeRequest.setOption(tradeOptionDetails);
        return tradeRequest;
    }

    private void logPriceResponse(PriceResponse priceResponse) {
        logger.info("Price Response: {}", priceResponse);
    }

    private void logTradeResponse(TradeResponse tradeResponse) {
        logger.info("Trade Response: {}", tradeResponse);
    }
}