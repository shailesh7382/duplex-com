## Disabling Mutual TLS (mTLS) in gRPC Client and Server

To disable mutual TLS (mTLS) in your gRPC client and server setup, you need to update the configuration to use plaintext communication instead of SSL/TLS. Follow the steps below to disable mTLS:

### Step 1: Update `GrpcServerConfig` to Disable mTLS

Modify the `GrpcServerConfig` class to use plaintext communication:

```java
package com.shailesh.config;

import com.shailesh.service.FXOptionServiceImpl;
import io.grpc.Server;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public Server grpcServer(FXOptionServiceImpl fxOptionService) {
        return NettyServerBuilder.forPort(grpcServerPort)
                .addService(fxOptionService)
                .usePlaintext() // Disable SSL/TLS
                .build()
                .start();
    }
}

package com.shailesh.config;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
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
        return NettyChannelBuilder.forAddress(grpcClientHost, grpcClientPort)
                .usePlaintext() // Disable SSL/TLS
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

```
# Server properties
grpc.server.port=9090
# grpc.server.certChainFilePath=path/to/server.crt
# grpc.server.privateKeyFilePath=path/to/server.key
# grpc.server.trustCertCollectionFilePath=path/to/ca.crt

# Client properties
grpc.client.host=localhost
grpc.client.port=9090
# grpc.client.certChainFilePath=path/to/client.crt
# grpc.client.privateKeyFilePath=path/to/client.key
# grpc.client.trustCertCollectionFilePath=path/to/ca.crt