package com.example.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
class CompositeController {
    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/portal/student/{id}")
    public String getPortalStudentInfo(@PathVariable String id) {
        String student = restTemplate.getForObject("http://localhost:8081/students/" + id, String.class);
        String courses = restTemplate.getForObject("http://localhost:8082/courses/student/" + id, String.class);
        return "{ \"student\": " + student + ", \"courses\": " + courses + " }";
    }
}

@SpringBootApplication
public class CompositeApplication {
    public static void main(String[] args) {
        SpringApplication.run(CompositeApplication.class, args);
    }
}
