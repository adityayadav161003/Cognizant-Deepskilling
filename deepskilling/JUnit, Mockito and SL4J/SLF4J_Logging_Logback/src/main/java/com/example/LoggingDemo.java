package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingDemo {
    private static final Logger logger = LoggerFactory.getLogger(LoggingDemo.class);

    public static void main(String[] args) {
        logger.info("Initializing application demo...");
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("Arithmetic exception caught: {}", e.getMessage(), e);
        }
    }
}
