package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

class UserDto {
    @NotBlank(message = "Username must not be blank")
    private String username;
    
    @Email(message = "Email must be valid")
    private String email;
}

@RestController
@RequestMapping("/api/users")
class UserController {
    @PostMapping
    public String registerUser(@Valid @RequestBody UserDto user) {
        return "User registration valid!";
    }
}

@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
