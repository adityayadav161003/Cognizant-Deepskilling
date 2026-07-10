# JUnit, Mockito, and SLF4J Deepskilling

This module covers Java unit testing, mocking dependencies, and logging frameworks.

## Topics Covered

1. **JUnit Basic Assertions** (`JUnit_Basic_Assertions`)
   - Writing standard JUnit tests, using basic assertions (`assertEquals`, `assertTrue`, `assertNotNull`), and testing exceptions.
2. **JUnit Advanced Execution Order** (`JUnit_Advanced_Execution_Order`)
   - Controlling test lifecycle and execution order using `@BeforeEach`, `@AfterEach`, `@BeforeAll`, `@AfterAll`, and order annotations.
3. **Mockito Basic Mocking & Stubbing** (`Mockito_Basic_Mocking_Stubbing`)
   - Initializing mocks, stubbing method calls using `when().thenReturn()`, and verifying mock interactions.
4. **Mockito Mock Dependencies Repository** (`Mockito_Mock_Dependencies_Repository`)
   - Injecting mocked repository dependencies into services and testing core business logic in isolation.
5. **Mockito Advanced Stub Returns** (`Mockito_Advanced_Stub_Returns`)
   - Dynamic stubs, argument matchers, throwing exceptions from mocks, and stubbing void methods.
6. **JUnit Spring Test MockMvc** (`JUnit_Spring_Test_MockMvc`)
   - Testing Spring controllers in isolation using MockMvc without spinning up the full server.
7. **SLF4J Logging with Logback** (`SLF4J_Logging_Logback`)
   - Configuring logging levels, logback XML format, and writing diagnostic logs across application layers.
