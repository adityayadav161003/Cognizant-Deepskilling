# Java 21 & Core Java Features Cheat Sheet

Quick reference for the modern Java features implemented throughout this module.

## 1. Virtual Threads (Lightweight Concurrency)
Introduced in Java 21, virtual threads are lightweight threads mapped to carrier platform threads. They dramatically reduce resources needed for high-concurrency applications.

### Code Pattern
```java
// Create and start a virtual thread
Thread virtualThread = Thread.startVirtualThread(() -> {
    System.out.println("Running on virtual thread: " + Thread.currentThread());
});
virtualThread.join();

// Creating a virtual thread executor
try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
    executor.submit(() -> "Task 1");
    executor.submit(() -> "Task 2");
} // auto-closes executor, awaits all tasks
```

## 2. Records (Immutable Data Carriers)
Records are special data classes designed to act as transparent data carriers. They automatically generate constructors, getters, `equals()`, `hashCode()`, and `toString()`.

### Code Pattern
```java
public record Employee(int id, String name, double salary) {
    // Optional compact constructor for validation
    public Employee {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
    }
}
```

## 3. Pattern Matching for Switch
Enhances `switch` statements and expressions to work on any type and evaluate structures with type patterns.

### Code Pattern
```java
public static String formatInput(Object obj) {
    return switch (obj) {
        case Integer i -> String.format("Integer value: %d", i);
        case Long l    -> String.format("Long value: %d", l);
        case Double d  -> String.format("Double value: %.4f", d);
        case String s  -> String.format("String value: %s", s);
        case null      -> "Null reference";
        default        -> obj.toString();
    };
}
```

## 4. HTTP Client API (Standardized since Java 11)
Replacing the legacy `HttpURLConnection`, the HttpClient supports HTTP/1.1, HTTP/2, synchronous/asynchronous requests, and web sockets.

### Code Pattern
```java
HttpClient client = HttpClient.newHttpClient();
HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://api.github.com/users/octocat"))
        .header("Accept", "application/json")
        .GET()
        .build();

// Synchronous Request
HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
System.out.println("Status Code: " + response.statusCode());
System.out.println("Response Body: " + response.body());
```
