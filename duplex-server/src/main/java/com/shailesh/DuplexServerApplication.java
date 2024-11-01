package com.shailesh;

import io.grpc.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DuplexServerApplication implements CommandLineRunner {

    @Autowired
    private Server grpcServer;

    public static void main(String[] args) {
        SpringApplication.run(DuplexServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        grpcServer.awaitTermination();
    }
}