// FxOptionApplicationTests.java
package com.example.fxoption;

import com.example.fxoption.entity.Price;
import com.example.fxoption.entity.Trade;
import com.example.fxoption.repository.PriceRepository;
import com.example.fxoption.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class FxOptionApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private TradeRepository tradeRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testGetPrice() throws Exception {
        Price price = new Price();
        price.setPriceId("1");
        price.setPriceRequestId("req1");
        price.setPrice(100.0);
        price.setStatus("PRICE_SUCCESS");
        priceRepository.save(price);

        String priceRequestJson = "{ \"priceId\": \"1\", \"priceRequestId\": \"req1\", \"cif\": \"12345\", \"option\": { \"underlyingCurrency\": \"USD\", \"strikeCurrency\": \"EUR\", \"strikePrice\": 1.2, \"expirationDate\": \"2023-01-01\", \"optionType\": \"CALL\", \"ccyPair\": \"USD/EUR\", \"quantity\": 1000, \"premium\": 10.0, \"tradeDate\": \"2023-01-01\", \"settlementDate\": \"2023-01-02\", \"counterparty\": \"Bank\", \"comments\": \"\", \"barrierType\": \"UP_AND_IN\", \"barrierLevel\": 1.1, \"lowerBarrierLevel\": 1.0, \"upperBarrierLevel\": 1.2 } }";
        mockMvc.perform(MockMvcRequestBuilders.post("/price/getPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(priceRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"priceId\": \"1\", \"priceRequestId\": \"req1\", \"price\": 100.0, \"status\": \"PRICE_SUCCESS\" }"));
    }

    @Test
    public void testTradeOption() throws Exception {
        Trade trade = new Trade();
        trade.setTradeId("1");
        trade.setQuantity(1000);
        trade.setPrice(100.0);
        trade.setStatus("TRADE_SUCCESS");
        trade.setReason("NONE");
        tradeRepository.save(trade);

        String tradeRequestJson = "{ \"tradeId\": \"1\", \"priceId\": \"1\", \"cif\": \"12345\", \"option\": { \"underlyingCurrency\": \"USD\", \"strikeCurrency\": \"EUR\", \"strikePrice\": 1.2, \"expirationDate\": \"2023-01-01\", \"optionType\": \"CALL\", \"ccyPair\": \"USD/EUR\", \"quantity\": 1000, \"premium\": 10.0, \"tradeDate\": \"2023-01-01\", \"settlementDate\": \"2023-01-02\", \"counterparty\": \"Bank\", \"comments\": \"\", \"barrierType\": \"UP_AND_IN\", \"barrierLevel\": 1.1, \"lowerBarrierLevel\": 1.0, \"upperBarrierLevel\": 1.2 }, \"quantity\": 1000, \"price\": 100.0, \"direction\": \"BUY\" }";

        mockMvc.perform(MockMvcRequestBuilders.post("/trade/tradeOption")
                .contentType(MediaType.APPLICATION_JSON)
                .content(tradeRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{ \"tradeId\": \"1\", \"quantity\": 1000, \"price\": 100.0, \"status\": \"TRADE_SUCCESS\", \"reason\": \"NONE\" }"));
    }
}