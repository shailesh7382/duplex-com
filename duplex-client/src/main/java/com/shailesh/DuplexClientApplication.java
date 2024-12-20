package com.shailesh;

import com.shailesh.service.FXOptionClientService;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class DuplexClientApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DuplexClientApplication.class);

    @Autowired
    private FXOptionClientService fxOptionClientService;

    @Autowired
    private ManagedChannel channel;

    public static void main(String[] args) {
        SpringApplication.run(DuplexClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        executeGrpcCalls();
        keepApplicationRunning();
        shutdownChannel();
    }

    private void executeGrpcCalls() {
        logger.info("Starting gRPC calls...");
        fxOptionClientService.callGetPrice(fxOptionClientService.createPriceRequest());
    }

     @Scheduled(fixedRate = 5000) // Send heartbeat every 5 seconds
    public void sendHeartbeat() {
        fxOptionClientService.sendHeartbeat();
    }

    private void keepApplicationRunning() throws InterruptedException {
        // Keep the application running to receive the streaming responses
        logger.info("Keeping the application running to receive streaming responses...");
        Thread.sleep(100000L);
    }

    private void shutdownChannel() {
        logger.info("Shutting down gRPC channel...");
        channel.shutdown();
    }
}