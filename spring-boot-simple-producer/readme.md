# spring-boot-simple-producer

A small Spring Boot service that acts as the **producer** in a Spring Cloud
Contract setup. It exposes an "even/odd" endpoint and publishes a **stub** that
the consumer project (`spring-boot-rest-jpa`) uses in its tests.

- **GAV:** `pl.ene:spring-boot-simple-producer:0.0.1-SNAPSHOT`
- **Endpoint:** `GET /validate/prime-number?number=<n>`
- **Swagger UI:** http://localhost:8080/swagger-ui/index.html

## Requirements

> **JDK 22 or newer is required to build this project.**
> The `spring-cloud-contract-maven-plugin` (4.1.4) requires Java 22+, even
> though the compiled bytecode targets Java 17. The build enforces this via
> `maven-enforcer-plugin` and fails fast with a clear message on an older JDK.

- JDK **22+** (e.g. Temurin 25)
- Maven **3.6.3+** (also enforced — the bundled wrapper may be older)

If you manage JDKs with SDKMAN:

```bash
export JAVA_HOME="$HOME/.sdkman/candidates/java/25.0.1-tem"
```

## Build & publish the stub

Building with `install` runs the contract tests and installs the stub JAR into
your local Maven repository (`~/.m2`), where the consumer picks it up:

```bash
mvn clean install
```

This produces and installs:

```
pl.ene:spring-boot-simple-producer:0.0.1-SNAPSHOT:stubs   (→ ~/.m2/repository/pl/ene/...)
```

## Run the service

```bash
mvn spring-boot:run
```

The app starts on **port 8080**.

## How the contract works

- The contract lives at
  `src/test/resources/contracts/shouldReturnEvenWhenRequestParamIsEven.groovy`
  and maps `GET /validate/prime-number?number=2` → body `Even`, status `200`.
- The base class for generated tests is `pl.ene.simpleproducer.BaseContractTest`.
- On `install`, the plugin generates `ContractVerifierTest` (verifies the real
  controller honours the contract) and packages the matching WireMock stub into
  the `*-stubs.jar`.

## Relationship to the consumer

`spring-boot-rest-jpa` references this stub in `SimpleProducerControllerTest` via:

```java
@AutoConfigureStubRunner(
    stubsMode = StubRunnerProperties.StubsMode.LOCAL,
    ids = "pl.ene:spring-boot-simple-producer:0.0.1-SNAPSHOT:stubs:8080")
```

So you must `mvn clean install` **this** project before the consumer's contract
test can run — the stub must exist in `~/.m2` first.
