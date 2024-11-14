package fxoption.controller;

import com.example.shailesh.fxoption.FxOptionProto.*;
import com.google.protobuf.util.JsonFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/price")
public class PriceController {

    @PostMapping(value = "/getPrice", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getPrice(@RequestBody String jsonRequest) throws Exception {
        // Convert JSON request to proto object
        PriceRequest.Builder requestBuilder = PriceRequest.newBuilder();
        JsonFormat.parser().merge(jsonRequest, requestBuilder);
        PriceRequest request = requestBuilder.build();

        // Create a response proto object
        PriceResponse response = PriceResponse.newBuilder()
                .setPriceId("price-1")
                .setPriceRequestId(request.getPriceRequestId())
                .setPrice(1.25)
                .setStatus(PriceRespStatus.PRICE_SUCCESS)
                .build();

        // Convert proto response to JSON
        String jsonResponse = JsonFormat.printer().print(response);

        return ResponseEntity.ok(jsonResponse);
    }
}