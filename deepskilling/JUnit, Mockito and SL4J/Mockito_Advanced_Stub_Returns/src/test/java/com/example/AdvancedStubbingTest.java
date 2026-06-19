package com.example;

import org.junit.Test;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

interface DatabaseConnection {
    void connect() throws Exception;
    String executeQuery(String query);
}

public class AdvancedStubbingTest {
    @Test(expected = RuntimeException.class)
    public void testConnectException() throws Exception {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        doThrow(new RuntimeException("Connection Failed")).when(conn).connect();
        conn.connect();
    }

    @Test
    public void testMultipleReturns() {
        DatabaseConnection conn = mock(DatabaseConnection.class);
        when(conn.executeQuery(anyString()))
            .thenReturn("Result 1")
            .thenReturn("Result 2");
            
        assertEquals("Result 1", conn.executeQuery("select 1"));
        assertEquals("Result 2", conn.executeQuery("select 1"));
    }
}
