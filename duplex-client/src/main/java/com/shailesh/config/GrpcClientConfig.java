package com.shailesh.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public com.example.shailesh.fxoption.FXOptionServiceGrpc.FXOptionServiceBlockingStub fxOptionServiceBlockingStub(ManagedChannel channel) {
        return com.example.shailesh.fxoption.FXOptionServiceGrpc.newBlockingStub(channel);
    }
}
