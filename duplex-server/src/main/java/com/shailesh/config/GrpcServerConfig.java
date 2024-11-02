package com.shailesh.config;

import com.shailesh.service.FXOptionServiceImpl;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLException;
import java.io.File;
import java.io.IOException;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Value("${grpc.server.certChainFilePath}")
    private String certChainFilePath;

    @Value("${grpc.server.privateKeyFilePath}")
    private String privateKeyFilePath;

    @Value("${grpc.server.trustCertCollectionFilePath}")
    private String trustCertCollectionFilePath;

    @Bean
    public Server grpcServer(FXOptionServiceImpl fxOptionService) throws SSLException {
        try {
            return NettyServerBuilder.forPort(grpcServerPort)
                    .addService(fxOptionService)
                    .sslContext(GrpcSslContexts.forServer(new File(certChainFilePath), new File(privateKeyFilePath))
                            .trustManager(new File(trustCertCollectionFilePath))
                            .clientAuth(io.grpc.netty.shaded.io.netty.handler.ssl.ClientAuth.REQUIRE)
                            .build())
                    .build()
                    .start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
                return null;
    }
}
