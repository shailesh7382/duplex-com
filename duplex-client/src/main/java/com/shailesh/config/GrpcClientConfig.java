package com.shailesh.config;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GrpcClientConfig {

    @Bean
    public ManagedChannel managedChannel() {
        return ManagedChannelBuilder.forAddress("localhost", 9090)
                                    .usePlaintext()
                                    .build();
    }

    @Bean
    public com.example.shailesh.fxoption.FXOptionServiceGrpc.FXOptionServiceBlockingStub fxOptionServiceBlockingStub(ManagedChannel channel) {
        return com.example.shailesh.fxoption.FXOptionServiceGrpc.newBlockingStub(channel);
    }
}
