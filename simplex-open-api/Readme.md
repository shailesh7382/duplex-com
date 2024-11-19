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

*** Forward-Compatible Changes: Ensure changes do not break existing clients.

- Avoid Renaming: Renaming fields can break existing clients.
- Preserve Enum Values: If you need to add new values to an enum, ensure that existing values are preserved.
- Preserve Field Order: Some serialization formats depend on the order of fields. Avoid reordering fields.
- Preserve Field Types: Changing a field's type can break clients that rely on the existing type.
- Preserve Field Semantics: Changing the meaning of a field can break clients that rely on the existing semantics.
- Preserve Service Signatures: Changing the request or response types of a service method can break clients that rely on the existing signatures.
- Preserve Enum Values: If you need to add new values to an enum, ensure that existing values are preserved.

** open api integration with spring boot

- read open api spec in openapi.yaml
- add open api dependencies. project adds for spring boot < 3.0.0
- generate api code and data exchange objects from open api spec