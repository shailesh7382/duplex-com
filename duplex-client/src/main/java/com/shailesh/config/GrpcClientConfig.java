package com.shailesh.config;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.File;

import com.example.shailesh.fxoption.FXOptionServiceGrpc;

@Configuration
public class GrpcClientConfig {

    @Value("${grpc.client.host}")
    private String grpcClientHost;

    @Value("${grpc.client.port}")
    private int grpcClientPort;

    @Value("${grpc.client.certChainFilePath}")
    private String certChainFilePath;

    @Value("${grpc.client.privateKeyFilePath}")
    private String privateKeyFilePath;

    @Value("${grpc.client.trustCertCollectionFilePath}")
    private String trustCertCollectionFilePath;

    @Bean
    public ManagedChannel managedChannel() throws SSLException {
        return NettyChannelBuilder.forAddress(grpcClientHost, grpcClientPort)
                .sslContext(GrpcSslContexts.forClient()
                        .keyManager(new File(certChainFilePath), new File(privateKeyFilePath))
                        .trustManager(new File(trustCertCollectionFilePath))
                        .build())
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
