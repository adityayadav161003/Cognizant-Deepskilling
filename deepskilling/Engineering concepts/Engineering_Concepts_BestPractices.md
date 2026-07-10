# Engineering Concepts - Design Patterns & Algorithmic Best Practices

Core guidelines and design recommendations for writing scalable algorithms and using design patterns effectively.

## 1. Algorithmic Complexity and Big-O Efficiency
Always evaluate time and space complexity of code execution:
- **Optimization Strategy**: Avoid nested loops ($O(N^2)$ complexity) when possible. Use hash maps/sets ($O(1)$ lookups) to reduce complexity to linear time ($O(N)$).
- **Recursion vs Iteration**: Be mindful of call stack limits when utilizing recursive logic. Use iterative methods or tail recursion optimization if memory limits are tight.

### Recommended Code Pattern (Search & Lookup Optimization)
```java
// Avoid searching nested lists; map entries first to achieve linear runtime
import java.util.*;

public class OrderLookupService {
    // Correct: Pre-build lookup map for O(1) retrieval instead of nesting loops
    public Map<String, Double> mapPrices(List<Item> items) {
        Map<String, Double> lookup = new HashMap<>();
        for (Item item : items) {
            lookup.put(item.sku(), item.price());
        }
        return lookup;
    }
}
```

## 2. Design Patterns Architecture
Apply clean object-oriented patterns to solve recurring structure challenges.

### Strategy Pattern (Encapsulating Algorithms)
Use the Strategy pattern to define a family of algorithms, encapsulate each one, and make them interchangeable at runtime.
```java
// Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy A
class CreditCardPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// Concrete Strategy B
class PayPalPayment implements PaymentStrategy {
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}
```

### Observer Pattern (Event Listening)
Define a one-to-many dependency between objects so that when one object changes state, all its dependents are notified automatically.
```java
import java.util.*;

class Subject {
    private List<Observer> observers = new ArrayList<>();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}

interface Observer {
    void update();
}
```

### Template Method Pattern (Defines Template Steps)
Define the skeleton of an algorithm in an operation, deferring some steps to subclasses.
```java
abstract class OrderProcessTemplate {
    public final void processOrder() {
        selectItem();
        pay();
        deliver(); // Hooks or abstract step implementations
    }
    
    abstract void selectItem();
    abstract void pay();
    abstract void deliver();
}
```
