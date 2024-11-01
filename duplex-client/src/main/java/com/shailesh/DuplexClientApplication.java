package com.shailesh;

import com.shailesh.service.FXOptionClientService;
import io.grpc.ManagedChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    private void keepApplicationRunning() throws InterruptedException {
        // Keep the application running to receive the streaming responses
        logger.info("Keeping the application running to receive streaming responses...");
        Thread.sleep(10000);
    }

    private void shutdownChannel() {
        logger.info("Shutting down gRPC channel...");
        channel.shutdown();
    }
}