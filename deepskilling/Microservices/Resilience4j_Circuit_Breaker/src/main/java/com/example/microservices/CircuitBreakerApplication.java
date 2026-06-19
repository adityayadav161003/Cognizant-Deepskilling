package com.example.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
class DemoController {
    @GetMapping("/data")
    @CircuitBreaker(name = "backendService", fallbackMethod = "fallbackData")
    public String getData() {
        throw new RuntimeException("Service failure simulation");
    }

    public String fallbackData(Throwable t) {
        return "Resilience4j Fallback Data Response";
    }
}

@SpringBootApplication
public class CircuitBreakerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CircuitBreakerApplication.class, args);
    }
}
