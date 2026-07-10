# Spring Data JPA with Hibernate Best Practices

Core guidelines and design recommendations for building robust database interaction layers using Spring Data JPA.

## 1. JPA Entity Relationships (Lazy Loading & Cascading)
- **FetchType.LAZY (Default for *-to-Many)**: Always use Lazy loading for collection associations (`@OneToMany`, `@ManyToMany`) to prevent loading unnecessary sub-records into memory.
- **Cascade Types**: Restrict Cascade types. Avoid `CascadeType.ALL` unless child records are completely lifecycle-bound to their parents (e.g., Invoice -> InvoiceLines).

### Recommended Code Pattern
```java
import jakarta.persistence.*;
import java.util.List;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Correct: Lazy fetch prevents N+1 query problem during basic Customer lookups
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Order> orders;
}
```

## 2. Custom Query Methods (JPQL vs Native SQL)
- **JPQL (Recommended)**: Use JPQL when querying object models. It handles schema migrations between database vendors automatically.
- **Native SQL**: Use only for database-specific optimizations or advanced dialect functions. Remember to set `nativeQuery = true`.

### Recommended Code Pattern
```java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // JPQL query matching Entity structure
    @Query("SELECT o FROM Order o WHERE o.status = :status AND o.amount > :minAmount")
    List<Order> findActiveHighValueOrders(
        @Param("status") String status, 
        @Param("minAmount") double minAmount
    );
}
```

## 3. Transaction Boundaries and Rollback Control
- **Declarative Transactions**: Annotate service methods carrying multi-statement DML updates with `@Transactional`.
- **Test Rollbacks**: Unit tests annotated with `@Transactional` automatically rollback changes on completion. To assert transactions or debug DB logs, use `@Rollback(false)` or inject `TestEntityManager`.

### Recommended Code Pattern
```java
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderProcessingService {
    private final OrderRepository orderRepository;
    private final InventoryRepository inventoryRepository;

    public OrderProcessingService(OrderRepository orderRepository, InventoryRepository inventoryRepository) {
        this.orderRepository = orderRepository;
        this.inventoryRepository = inventoryRepository;
    }

    // Atomic transaction - rollback occurs automatically if inventory fails to deduct
    @Transactional
    public void checkout(Long orderId, Long itemId, int quantity) {
        inventoryRepository.deductStock(itemId, quantity);
        orderRepository.updateStatus(orderId, "COMPLETED");
    }
}
```
