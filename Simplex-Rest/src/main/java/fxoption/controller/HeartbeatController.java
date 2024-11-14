package fxoption.controller;

import com.example.shailesh.fxoption.FxOptionProto.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/heartbeat")
public class HeartbeatController {

    @PostMapping("/ping")
    public HeartbeatResponse heartbeat(@RequestBody HeartbeatRequest request) {
        return HeartbeatResponse.newBuilder()
                .setMessage("pong")
                .setTimestamp(System.currentTimeMillis())
                .build();
    }
}