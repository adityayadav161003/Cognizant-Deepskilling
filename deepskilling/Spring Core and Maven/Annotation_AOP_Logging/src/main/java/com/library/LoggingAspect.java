package com.library;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.library.BookService.*(..))")
    public void logBefore() {
        System.out.println("[ANNOTATION-AOP-LOG]: Method invocation monitored and logged successfully.");
    }
}
