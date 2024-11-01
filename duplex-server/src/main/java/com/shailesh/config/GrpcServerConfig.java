package com.shailesh.config;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shailesh.FXOptionServiceImpl;

import java.io.IOException;

@Configuration
public class GrpcServerConfig {

    @Bean
    public Server grpcServer(FXOptionServiceImpl fxOptionService) throws IOException {
        return ServerBuilder.forPort(9090)
                .addService(fxOptionService)
                .build()
                .start();
    }
}
