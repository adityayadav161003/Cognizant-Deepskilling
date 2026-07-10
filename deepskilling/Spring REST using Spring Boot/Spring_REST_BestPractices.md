# Spring Boot REST API Design and Security Best Practices

Best practices and patterns for building robust RESTful microservices, handling validation exceptions, and securing endpoints.

## 1. REST Endpoint Architecture & Status Codes
Use correct HTTP Methods (`GET`, `POST`, `PUT`, `DELETE`) and return precise HTTP status codes (`200 OK`, `201 Created`, `400 Bad Request`, `404 Not Found`, `500 Internal Server Error`).

### Recommended Code Pattern
```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product savedProduct = productService.save(product);
        // Correct: Return 201 Created status code on successful insertion
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }
}
```

## 2. Jakarta Validation Errors
Validate incoming JSON request payloads on the controllers using Jakarta validation constraints (`@NotNull`, `@Size`, `@Min`, `@Email`). Mark input parameters with `@Valid`.

### Recommended Code Pattern
```java
import jakarta.validation.constraints.*;

public class UserRegistrationDto {
    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    private String username;

    @Email(message = "Please supply a valid email address")
    private String email;
}
```

## 3. Global Exception Handling (`@ControllerAdvice`)
Implement a unified global exception handling mechanism to intercept exceptions and format uniform JSON error structures for client consumption.

### Recommended Code Pattern
```java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Catch validation failures and format them as readable lists
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        List<String> errors = ex.getBindingResult()
                                .getFieldErrors()
                                .stream()
                                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                                .toList();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("errors", errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
```

## 4. Stateless Security with JSON Web Tokens (JWT)
Secure Rest APIs using Spring Security filters with stateless authentication. Use JWTs to identify authenticated users without keeping session state on servers.

### Recommended Code Pattern (JWT Filtering)
```java
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = parseJwt(request);
        if (token != null && jwtProvider.validateToken(token)) {
            var auth = jwtProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }
}
```
