package com.example.fxoption.client;

import com.example.fxoption.model.PriceRequest;
import com.example.fxoption.model.PriceResponse;
import com.example.fxoption.model.TradeRequest;
import com.example.fxoption.model.TradeResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class FxOptionWebClient {

    private final WebClient webClient;

    @Value("${fxoption.price.url}")
    private String priceUrl;

    @Value("${fxoption.trade.url}")
    private String tradeUrl;

    public FxOptionWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public Mono<PriceResponse> getPrice(PriceRequest priceRequest) {
        return webClient.post()
                .uri(priceUrl)
                .bodyValue(priceRequest)
                .retrieve()
                .bodyToMono(PriceResponse.class);
    }

    public Mono<TradeResponse> tradeOption(TradeRequest tradeRequest) {
        return webClient.post()
                .uri(tradeUrl)
                .bodyValue(tradeRequest)
                .retrieve()
                .bodyToMono(TradeResponse.class);
    }
}