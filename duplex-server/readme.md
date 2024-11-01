# DuplexServerApplication

## Overview
The `DuplexServerApplication` module is a Spring Boot application that serves as a gRPC server. It is responsible for handling incoming gRPC requests defined in the `fxoption.proto` file and providing appropriate responses. This module includes the necessary configuration to start the gRPC server and keep it running.

## Key Components

1. **Spring Boot Application**: The module is a Spring Boot application, which simplifies the configuration and setup of the application.
2. **gRPC Server**: The module includes a gRPC server that listens for incoming gRPC requests and processes them using the service implementations.

## Detailed Description

### 1. `DuplexServerApplication` Class

- **Purpose**: This is the main class that bootstraps the Spring Boot application and starts the gRPC server.
- **Annotations**: 
  - `@SpringBootApplication`: Indicates that this is a Spring Boot application.
  - `@Autowired`: Injects the gRPC server instance into the class.
- **Methods**:
  - `main(String[] args)`: The entry point of the application. It runs the Spring Boot application.
  - `run(String... args)`: This method is executed after the application context is loaded. It calls `grpcServer.awaitTermination()` to keep the gRPC server running.

### 2. `GrpcServerConfig` Class

- **Purpose**: This class is responsible for configuring and starting the gRPC server.
- **Annotations**:
  - `@Configuration`: Indicates that this class contains Spring configuration.
  - `@Bean`: Defines beans for the gRPC server and service implementations.
- **Methods**:
  - `grpcServer(FXOptionServiceImpl fxOptionService)`: Configures and starts the gRPC server with the provided service implementation.

### 3. `FXOptionServiceImpl` Class

- **Purpose**: This class implements the gRPC service defined in the `fxoption.proto` file.
- **Annotations**:
  - `@Service`: Indicates that this class is a Spring service component.
- **Methods**:
  - `getPrice(PriceRequest request, StreamObserver<PriceResponse> responseObserver)`: Handles the `GetPrice` gRPC request.
  - `tradeOption(TradeRequest request, StreamObserver<TradeResponse> responseObserver)`: Handles the `TradeOption` gRPC request.


This module is responsible for handling gRPC requests and providing appropriate responses, making it a crucial part of the duplex communication system.