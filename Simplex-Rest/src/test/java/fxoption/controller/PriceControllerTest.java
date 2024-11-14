package fxoption.controller;

import com.example.shailesh.fxoption.FxOptionProto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(PriceController.class)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceController priceController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetPrice() throws Exception {
        PriceRequest request = PriceRequest.newBuilder()
                .setPriceRequestId("request-1")
                .build();

        PriceResponse response = PriceResponse.newBuilder()
                .setPriceId("price-1")
                .setPriceRequestId("request-1")
                .setPrice(1.25)
                .setStatus(PriceRespStatus.PRICE_SUCCESS)
                .build();

        String jsonResponse = objectMapper.writeValueAsString(response);
        ResponseEntity<String> responseEntity = ResponseEntity.ok(jsonResponse);

        when(priceController.getPrice(any(String.class))).thenReturn(responseEntity);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/price/getPrice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().json(jsonResponse));
    }
}