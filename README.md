# Tabz

A digital receipt app. Shopowner uploads a PDF receipt (via Postman),
a QR code is generated. Customer scans the QR code and claims the receipt.

## Tech stack
- Java 21
- Spring Boot 3
- Spring Data JPA
- Thymeleaf
- ZXing (QR code generation)
- Postgres

## Run locally
1. Clone the repo
2. Set `app.base-url` in `application.properties`
3. Setup postgres database: tabz
4. Run with `./mvnw spring-boot:run`