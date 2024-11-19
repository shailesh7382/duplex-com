simplex-open-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── fxoption/
│   │   │               ├── FxOptionApplication.java
│   │   │               ├── controller/
│   │   │               │   ├── PriceController.java
│   │   │               │   └── TradeController.java
│   │   │               ├── entity/
│   │   │               │   ├── Price.java
│   │   │               │   └── Trade.java
│   │   │               ├── repository/
│   │   │               │   ├── PriceRepository.java
│   │   │               │   └── TradeRepository.java
│   │   │               └── service/
│   │   │                   ├── PriceService.java
│   │   │                   └── TradeService.java
│   │   ├── resources/
│   │   │   ├── application.yaml
│   │   │   └── openapi.yaml
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── fxoption/
│                       └── FxOptionApplicationTests.java


*** Backward-Compatible Changes: Ensure changes are additive and do not remove or alter existing functionality.  

- Add New Fields: Adding new fields to responses is generally safe.
- Optional Parameters: Add new optional parameters to requests.