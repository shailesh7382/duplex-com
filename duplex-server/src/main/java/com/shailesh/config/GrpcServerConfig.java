package com.shailesh.config;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shailesh.service.FXOptionServiceImpl;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public Server grpcServer(FXOptionServiceImpl fxOptionService) throws IOException {
        return ServerBuilder.forPort(grpcServerPort)
                .addService(fxOptionService)
                .build()
                .start();
    }
}
