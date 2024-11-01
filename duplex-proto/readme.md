# FXOption Protocol Buffers (fxoption.proto)

## Overview
The `fxoption.proto` file defines the protocol buffer messages and gRPC services for the FXOption trading system. This file is used to generate the necessary gRPC stubs and message classes for both the client and server applications. It includes definitions for various messages, enums, and services related to FXOption trading.

## Key Components

1. **Enums**: Defines various enumerations used in the FXOption trading system.
2. **Messages**: Defines the structure of the data exchanged between the client and server.
3. **Services**: Defines the gRPC services and their methods.

## Detailed Description

### Enums

#### FXOptionType
Represents the type of FXOption.
- `CALL = 0`: Call option.
- `PUT = 1`: Put option.

#### BuySell
Represents the direction of the trade.
- `BUY = 0`: Buy direction.
- `SELL = 1`: Sell direction.

#### TradeState
Represents the state of the trade.
- `TRADE_SUCCESS = 0`: Trade was successful.
- `TRADE_FAILURE = 1`: Trade failed.
- `PENDING = 2`: Trade is pending.

#### PriceRespStatus
Represents the status of the price response.
- `PRICE_SUCCESS = 0`: Price response was successful.
- `PRICE_FAILURE = 1`: Price response failed.

#### TradeStatusReason
Represents the reason for the trade status.
- `NONE = 0`: No specific reason.
- `INSUFFICIENT_FUNDS = 1`: Insufficient funds.
- `MARKET_CLOSED = 2`: Market is closed.
- `INVALID_QUANTITY = 3`: Invalid quantity.
- `OTHER = 4`: Other reasons.