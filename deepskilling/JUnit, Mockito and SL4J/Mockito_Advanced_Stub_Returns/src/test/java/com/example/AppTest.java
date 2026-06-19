package com.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

interface Repository { String getData(); }
class Service {
    private Repository repo;
    public Service(Repository r) { this.repo = r; }
    public String processData() { return "Processed " + repo.getData(); }
}

public class ServiceTest {
    @Test
    public void testServiceWithMockRepository() {
        Repository mockRepository = mock(Repository.class);
        when(mockRepository.getData()).thenReturn("Mock Data");
        Service service = new Service(mockRepository);
        assertEquals("Processed Mock Data", service.processData());
    }
}
