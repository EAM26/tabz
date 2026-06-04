# Tabz

A digital receipt app. Upload a PDF receipt via Postman,
a QR code is generated. Customer scans the QR code and claims the receipt.

## Tech stack
- Java 21
- Spring Boot 3
- Spring Data JPA
- Thymeleaf
- ZXing (QR code generation)
- H2

## Run locally
1. Clone the repo
2. Set `app.base-url` in `application.properties`
3. Run with `./mvnw spring-boot:run`