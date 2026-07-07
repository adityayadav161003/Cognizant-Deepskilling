package com.library;

public class LoggingAspect {
    public void beforeAdvice() {
        System.out.println("[XML-AOP-LOG] Before Advice executed: Logged service invocation.");
    }
}
