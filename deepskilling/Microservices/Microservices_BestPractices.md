# Microservices Architecture Best Practices Guide

Key architectural patterns and deployment guidelines for service discovery, gateway routing, config management, and fault tolerance.

## 1. Service Discovery (Eureka Registry)
Microservices must register with a Service Registry to lookup addresses dynamically, avoiding hardcoded network locations.
- **Self-Preservation Mode**: In production, do not disable Eureka's self-preservation to ensure the registry doesn't drop active service instances during network disruptions.

### Recommended Configuration Pattern (`application.yml`)
```yaml
eureka:
  client:
    register-with-eureka: true
    fetch-registry: true
    service-url:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
```

## 2. API Gateway Routing
Route external clients through a secure Gateway rather than allowing direct microservice exposure.
- **Routing & Filters**: Apply Global Filters for authentication, request tracking, and request/response logging.

### Recommended Configuration Pattern (`application.yml`)
```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: order-service
          uri: lb://ORDER-SERVICE
          predicates:
            - Path=/api/orders/**
          filters:
            - StripPrefix=1
```

## 3. Distributed Config Server
Externalize and centralize runtime properties using Spring Cloud Config Server.
- **Local Fallback**: Configure client configurations to look up locally or retry if the Config Server is briefly unreachable during boot.

### Recommended Client Configuration (`bootstrap.yml`)
```yaml
spring:
  application:
    name: order-service
  cloud:
    config:
      uri: http://localhost:8888
      fail-fast: true
      retry:
        initial-interval: 2000
        max-attempts: 5
```

## 4. Resilience4j Circuit Breaker (Fault Tolerance)
Protect upstream systems from cascading failures by encapsulating remote calls inside a Circuit Breaker.
- **Fallback Methods**: Always supply a fallback handler to serve cached data or generic error payloads if the remote microservice is offline.

### Recommended Code Pattern
```java
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @CircuitBreaker(name = "paymentService", fallbackMethod = "paymentFallback")
    public String processPayment(String orderId) {
        // Call remote Payment Microservice
        return remotePaymentClient.authorize(orderId);
    }

    public String paymentFallback(String orderId, Throwable ex) {
        // Safe fallback action on failure or circuit trip
        return "Payment service unavailable. Order: " + orderId + " queued for processing.";
    }
}
```
