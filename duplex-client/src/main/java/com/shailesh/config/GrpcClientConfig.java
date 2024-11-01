package com.shailesh.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.shailesh.fxoption.FXOptionServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Value("${grpc.client.host}")
    private String grpcClientHost;

    @Value("${grpc.client.port}")
    private int grpcClientPort;

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress(grpcClientHost, grpcClientPort)
                                    .usePlaintext()
                                    .build();
    }

    @Bean
    public FXOptionServiceGrpc.FXOptionServiceStub fxOptionServiceStub(ManagedChannel channel) {
        return FXOptionServiceGrpc.newStub(channel);
    }

    @Bean
    public FXOptionServiceGrpc.FXOptionServiceBlockingStub fxOptionServiceBlockingStub(ManagedChannel channel) {
        return FXOptionServiceGrpc.newBlockingStub(channel);
    }
}
