package com.example.fxoption.client;

import com.example.fxoption.model.PriceRequest;
import com.example.fxoption.model.PriceResponse;
import com.example.fxoption.model.TradeRequest;
import com.example.fxoption.model.TradeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class FxOptionClient {

    private static final Logger logger = LoggerFactory.getLogger(FxOptionClient.class);

    @Value("${fxoption.price.url}")
    private String priceUrl;

    @Value("${fxoption.trade.url}")
    private String tradeUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public FxOptionClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.restTemplate.setRequestFactory(clientHttpRequestFactory());
    }

    private ClientHttpRequestFactory clientHttpRequestFactory() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(5000);
        factory.setReadTimeout(5000);
        return factory;
    }

    public PriceResponse getPrice(PriceRequest priceRequest) {
        try {
            return restTemplate.postForObject(priceUrl, priceRequest, PriceResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error occurred while fetching price: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw e;
        }
    }

    public TradeResponse tradeOption(TradeRequest tradeRequest) {
        try {
            return restTemplate.postForObject(tradeUrl, tradeRequest, TradeResponse.class);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error occurred while trading option: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred: {}", e.getMessage());
            throw e;
        }
    }
}