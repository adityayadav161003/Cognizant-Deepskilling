package com.example.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaApplicationTest {
    @Autowired
    private CustomerRepository repo;

    @Test
    public void testSaveCustomer() {
        Customer c = new Customer("Alice", "alice@example.com");
        Customer saved = repo.save(c);
        assertThat(saved.getId()).isNotNull();
    }
}
