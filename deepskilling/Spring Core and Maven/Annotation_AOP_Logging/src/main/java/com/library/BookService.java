package com.library;

import org.springframework.stereotype.Service;

@Service
public class BookService {
    public void performCheckout() {
        System.out.println("Processing user book checkout transaction...");
    }
}
