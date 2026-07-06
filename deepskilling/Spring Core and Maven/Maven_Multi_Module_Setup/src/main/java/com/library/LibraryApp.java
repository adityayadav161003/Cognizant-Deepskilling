package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class BookService {
    public void serviceInfo() {
        System.out.println("BookService active for exercise 8");
    }
}

public class LibraryApp {
    public static void main(String[] args) {
        System.out.println("Initializing Library Management Spring App...");
    }
}
