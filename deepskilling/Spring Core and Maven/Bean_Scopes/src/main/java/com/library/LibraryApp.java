package com.library;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LibraryApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookService singleton1 = (BookService) context.getBean("singletonService");
        BookService singleton2 = (BookService) context.getBean("singletonService");
        System.out.println("Singleton Identity Match: " + (singleton1 == singleton2)); // true

        BookService prototype1 = (BookService) context.getBean("prototypeService");
        BookService prototype2 = (BookService) context.getBean("prototypeService");
        System.out.println("Prototype Identity Match: " + (prototype1 == prototype2)); // false
    }
}
