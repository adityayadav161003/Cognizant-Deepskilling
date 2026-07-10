# Spring Core and Maven Development Best Practices

Best practices and guidelines for using Spring Core IOC container, AOP logging, and organizing multi-module Maven projects.

## 1. Dependency Injection - Constructor vs Setter
- **Constructor Injection (Recommended)**: Ensures required dependencies are not null, allows dependencies to be final (immutable), and simplifies unit testing since mock references can be passed into the constructor directly.
- **Setter Injection**: Use only for optional dependencies that can be set or changed at runtime.

### Recommended Code Pattern
```java
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    // Correct: Constructor Injection (No @Autowired required in modern Spring)
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

## 2. Bean Lifecycles and Scopes
Understand and use appropriate scopes to prevent memory leaks and multi-threaded race conditions:
- **Singleton (Default)**: Stateless beans. Must be thread-safe.
- **Prototype**: State-carrying beans. A new instance is created on every lookup request.
- **Request / Session**: Web-scoped beans scoped to HTTP requests or user sessions.

### Recommended Code Pattern
```java
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype") // New instance generated for each injection lookup
public class ExecutionTracker {
    private double executionTime;
    
    public void track(double duration) {
        this.executionTime += duration;
    }
}
```

## 3. Aspect-Oriented Programming (AOP) for System Audits
Decouple cross-cutting concerns like logging, transactions, and security checks from primary business classes using Aspects.
- **JoinPoints**: Prefer logging execution metrics around `@Around` joinpoints to track both entry/exit times and log exceptions.

### Recommended Code Pattern
```java
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(PerformanceLoggingAspect.class);

    @Around("execution(* com.example.service.*.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        long executionTime = System.currentTimeMillis() - start;
        logger.info("{} executed in {}ms", joinPoint.getSignature(), executionTime);
        return proceed;
    }
}
```

## 4. Multi-Module Maven Projects
For larger enterprise systems, structure code as a Maven multi-module project to separate concerns (e.g., domain, repository, service, web API).
- **Dependency Management**: Use `<dependencyManagement>` inside the root POM to declare dependencies and lock down versions. Child POMs should import dependencies without specifying version numbers.
