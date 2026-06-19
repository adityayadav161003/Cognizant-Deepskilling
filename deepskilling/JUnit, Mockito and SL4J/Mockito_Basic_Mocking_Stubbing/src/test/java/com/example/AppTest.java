package com.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

interface ExternalApi { String getData(); }
class MyService {
    private ExternalApi api;
    public MyService(ExternalApi api) { this.api = api; }
    public String fetchData() { return api.getData(); }
}

public class MyServiceTest {
    @Test
    public void testExternalApi() {
        ExternalApi mockApi = mock(ExternalApi.class);
        when(mockApi.getData()).thenReturn("Mock Data");
        MyService service = new MyService(mockApi);
        assertEquals("Mock Data", service.fetchData());
    }
}
