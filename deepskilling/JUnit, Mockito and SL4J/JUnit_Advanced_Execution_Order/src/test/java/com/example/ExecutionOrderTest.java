package com.example;

import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ExecutionOrderTest {
    
    @BeforeClass
    public static void setUpBeforeClass() {
        System.out.println("BeforeClass: Executed once before all tests.");
    }

    @AfterClass
    public static void tearDownAfterClass() {
        System.out.println("AfterClass: Executed once after all tests.");
    }

    @Before
    public void setUp() {
        System.out.println("Before: Executed before each test method.");
    }

    @After
    public void tearDown() {
        System.out.println("After: Executed after each test method.");
    }

    @Test
    public void testA_FirstScenario() {
        System.out.println("Running Test A");
        Assert.assertTrue(true);
    }

    @Test
    public void testB_SecondScenario() {
        System.out.println("Running Test B");
        Assert.assertTrue(true);
    }
}
