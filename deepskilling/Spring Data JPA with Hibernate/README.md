# Spring Data JPA with Hibernate Deepskilling

This module covers Object-Relational Mapping (ORM) using Hibernate, database access abstractions via Spring Data JPA, and testing database layers.

## Exercises Covered

1. **Basic JPA Entity and Repository** (`Basic_JPA_Entity_and_Repository`)
   - Creating standard JPA entities with annotations (`@Entity`, `@Id`, `@GeneratedValue`), and setting up basic CrudRepository interfaces.
2. **Custom Query Methods** (`Custom_Query_Methods`)
   - Implementing derived query methods, `@Query` annotations (JPQL and Native SQL), and dynamic query methods.
3. **JPA Unit Testing** (`JPA_Unit_Testing`)
   - Writing slice tests for the database repository layer using `@DataJpaTest`, configuring in-memory H2 databases, and writing unit test assertions.
4. **Transaction Rollback Testing** (`Transaction_Rollback_Testing`)
   - Investigating Spring transaction management, utilizing `@Transactional`, and verifying database state recovery on method rollback actions.
