## Overview
The `duplex-com` project is a comprehensive system for FXOption trading, utilizing gRPC for communication between client and server applications. The project includes both client and server modules, with the client sending requests for price quotes and trade executions, and the server responding with the requested information and processing trades.

## Project Structure

### 1. `fxoption.proto`
The `fxoption.proto` file defines the protocol buffer messages and gRPC services for the FXOption trading system. This file is used to generate the necessary gRPC stubs and message classes for both the client and server applications.

#### Key Components
- **Enums**: Defines various enumerations used in the FXOption trading system.
- **Messages**: Defines the structure of the data exchanged between the client and server.
- **Services**: Defines the gRPC services and their methods.

#### Detailed Description
- **Enums**: Includes `FXOptionType`, `BuySell`, `TradeState`, `PriceRespStatus`, and `TradeStatusReason`.
- **Messages**: Includes `Date`, `FXOptionDetails`, `PriceRequest`, `PriceResponse`, `TradeRequest`, and `TradeResponse`.
- **Services**: Defines the `FXOptionService` with `GetPrice` and `TradeOption` RPC methods.

### 2. `DuplexServerApplication`
The `DuplexServerApplication` module is a Spring Boot application that serves as a gRPC server. It handles incoming gRPC requests defined in the `fxoption.proto` file and provides appropriate responses.

#### Key Components
- **Spring Boot Application**: Simplifies the configuration and setup of the application.
- **gRPC Server**: Listens for incoming gRPC requests and processes them using the service implementations.

#### Detailed Description
- **`DuplexServerApplication` Class**: Bootstraps the Spring Boot application and starts the gRPC server.
- **`GrpcServerConfig` Class**: Configures and starts the gRPC server.
- **`FXOptionServiceImpl` Class**: Implements the gRPC service defined in the `fxoption.proto` file.

### 3. `DuplexClientApplication`
The `DuplexClientApplication` module is a Spring Boot application that acts as a gRPC client. It sends requests for price quotes and trade executions to the server and handles the responses.

#### Key Components
- **Spring Boot Application**: Simplifies the configuration and setup of the application.
- **gRPC Client**: Sends requests to the gRPC server and handles the responses.

#### Detailed Description
- **`DuplexClientApplication` Class**: Bootstraps the Spring Boot application and manages the gRPC client operations.
- **`GrpcClientConfig` Class**: Configures the gRPC client.
- **`FXOptionClientService` Class**: Handles the gRPC client operations, including creating price requests, handling price responses, and executing trade requests.


Running the Project

```sh
git clone https://github.com/your-repo/duplex-com.git

cd duplex-com
mvn clean install

cd duplex-server
mvn spring-boot:run

cd duplex-client
mvn spring-boot:run