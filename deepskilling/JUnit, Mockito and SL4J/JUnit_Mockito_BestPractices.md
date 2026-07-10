# JUnit, Mockito and SLF4J Best Practices Guide

Best practices for writing robust automated unit tests using JUnit 5, Mockito, MockMvc, and configuring system logs.

## 1. JUnit 5 Assertions and Execution Order
- **Descriptive Assertions**: Always pass assertion explanations. Use `assertThrows` to test expected exceptional behaviors.
- **Ordered Execution**: Avoid ordering unit tests unless specifically required for integration scenarios. If necessary, use `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`.

### Recommended Code Pattern
```java
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("User Domain Tests")
class UserTest {
    @Test
    @DisplayName("Should raise exception for negative inputs")
    void testNegativeBalanceException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Account(-50);
        }, "Constructing accounts with negative balances must fail.");
    }
}
```

## 2. Mockito Mocking and Stubbing
- **Strict Stubbing**: Ensure all stubbed methods are actually invoked during the test execution. Mockito 2+ enforces this by default to prevent unused stubs.
- **Verification**: Use `verify` to assert interactions, especially on void methods.

### Recommended Code Pattern
```java
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testRegisterUser() {
        User user = new User("admin", "admin@example.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        userService.register("admin", "admin@example.com");

        verify(userRepository, times(1)).save(any(User.class));
    }
}
```

## 3. MockMvc API Endpoint Testing
Use `MockMvc` to perform integration tests on the web layer without launching a full servlet container.

### Recommended Code Pattern
```java
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetHelloEndpoint() throws Exception {
        mockMvc.perform(get("/api/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }
}
```

## 4. SLF4J / Logback Logging Architecture
- **Parameterized Logging**: Avoid string concatenation inside log statements to save performance. Use placeholders `{}`.
- **Log Levels**: Use correct levels (`TRACE` / `DEBUG` for developers, `INFO` for operational states, `WARN` for recoverable errors, `ERROR` for system failures).

### Recommended Code Pattern
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogManager {
    private static final Logger logger = LoggerFactory.getLogger(LogManager.class);

    public void processData(String payloadId, int itemsCount) {
        // Correct: Parameterized placeholders
        logger.info("Processing payload ID: {} with items count: {}", payloadId, itemsCount);
    }
}
```
