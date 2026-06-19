package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

interface ProductRepository {
    int getStock(String id);
}

class ProductService {
    private ProductRepository repo;
    public ProductService(ProductRepository r) { this.repo = r; }
    public boolean checkStock(String id) {
        return repo.getStock(id) > 0;
    }
}

@RunWith(MockitoJUnitRunner.class)
public class DependencyMockingTest {
    @Mock
    private ProductRepository repo;

    @InjectMocks
    private ProductService service;

    @Test
    public void testCheckStock() {
        when(repo.getStock("P100")).thenReturn(5);
        assertTrue(service.checkStock("P100"));
        
        when(repo.getStock("P200")).thenReturn(0);
        assertFalse(service.checkStock("P200"));
    }
}
