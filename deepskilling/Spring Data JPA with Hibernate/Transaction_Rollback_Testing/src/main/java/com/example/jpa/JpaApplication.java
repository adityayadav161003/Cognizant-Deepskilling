package com.example.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
class CustomerService {
    @Autowired
    private CustomerRepository repo;
    
    @Transactional
    public void saveWithRollback(Customer c) {
        repo.save(c);
        throw new RuntimeException("Force rollback trigger");
    }
}

@SpringBootApplication
public class JpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }
}
