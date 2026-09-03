# SpringCloudProductServiceconfigclient

A product service that gets its configuration from the config server instead of its own
`application.properties`.

## What is in here

- `ProductRestController.java` — the endpoints, reading values that arrived from outside the app
- `application.properties` — deliberately almost empty, which is the point:
  ```properties
  server.port=9940
  spring.application.name=PRODUCT-SERVICE
  eureka.client.service-url.defaultZone=http://localhost:8761/eureka
  spring.config.import=optional:configserver:http://localhost:8888
  management.endpoints.web.exposure.include=*
  ```

`spring.application.name` does double duty: it is the Eureka identity **and** the filename the
config server looks for in its git repo.

`optional:` on the import means the service still boots when the config server is unreachable.
Without it, a config server outage takes every client down with it.

`RefreshScopeApplicationProperties` is this same service — same port, same name — with
`@RefreshScope` added, so it can pick up changed configuration without a restart.

## The set this belongs to

These were written as one system, not as isolated samples. **Nothing registers until the Eureka
server is up**, so start it first.

| Repo | Port | `spring.application.name` | Role |
|---|---|---|---|
| `SpringCloudEurekaServerApplication` | 8761 | — | Service registry — **start first** |
| `SpringCloudConfigServerApplication` | 8888 | — | Git-backed configuration server |
| `SpringCloudApiGateway` | 8080 | `GATEWAY-SERVICE` | The single entry point |
| `SpringCloudCartServiceApplicationEurekaExample` | 9009 | `CART-SERVICE` | Cart and book endpoints |
| `SpringCloudPaymentServiceApplicationEurekaeg` | 8989 | `PAYMENT-SERVICE` | Payment, calling out via Feign |
| `SpringCloudProductServiceconfigclient` | 9940 | `PRODUCT-SERVICE` | Reads its config from the config server |
| `RefreshScopeApplicationProperties` | 9940 | `PRODUCT-SERVICE` | The same product service, plus `@RefreshScope` |
| `SpringCloudPaymentCircuitBreaker` | 9898 | `PAYMENT-APP` | Payment again, wrapped in Hystrix |
| `Resilience4j` | — | — | What to use instead of Hystrix, which is end-of-life |

## Running it

```bash
./mvnw spring-boot:run
```

---

One of a set of small repositories I wrote while learning the Java/Spring ecosystem. Each one
exists to get a single idea working end to end, so it is deliberately minimal — no tests worth the
name, no production hardening. Kept public because the commit history is a more honest record of
what I learned than a summary would be.
