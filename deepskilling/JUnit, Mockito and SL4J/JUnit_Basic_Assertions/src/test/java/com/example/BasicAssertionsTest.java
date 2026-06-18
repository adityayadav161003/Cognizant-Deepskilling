package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class BasicAssertionsTest {
    @Test
    public void testBasicMath() {
        int expected = 10;
        int actual = 5 + 5;
        assertEquals("Basic addition failed", expected, actual);
    }

    @Test
    public void testArrayEquality() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals("Arrays are not equal", expected, actual);
    }

    @Test
    public void testNullsAndBoolean() {
        Object obj = null;
        assertNull("Object should be null", obj);
        assertTrue("Condition should be true", 5 > 3);
        assertFalse("Condition should be false", 3 > 5);
    }
}
