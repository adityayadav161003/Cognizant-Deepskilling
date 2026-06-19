package com.example;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

interface DataService {
    String retrieveData();
}

class BusinessService {
    private DataService service;
    public BusinessService(DataService s) { this.service = s; }
    public String getUpperCaseData() {
        return service.retrieveData().toUpperCase();
    }
}

public class BasicMockingTest {
    @Test
    public void testMockedService() {
        DataService mockService = mock(DataService.class);
        when(mockService.retrieveData()).thenReturn("hello mocked world");
        
        BusinessService business = new BusinessService(mockService);
        String result = business.getUpperCaseData();
        
        assertEquals("HELLO MOCKED WORLD", result);
        verify(mockService, times(1)).retrieveData();
    }
}
