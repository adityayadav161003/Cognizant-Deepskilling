package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

class Book {
    private String isbn;
    private String title;
}

@RestController
@RequestMapping("/api/books")
class BookRestController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createBook(@RequestBody Book book) {
        return "Book successfully created!";
    }
}

@SpringBootApplication
public class RestApplication {
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }
}
