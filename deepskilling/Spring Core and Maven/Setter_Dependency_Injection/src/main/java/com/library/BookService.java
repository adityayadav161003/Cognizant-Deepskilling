package com.library;

public class BookService {
    private BookRepository repository;
    public void setRepository(BookRepository repo) {
        this.repository = repo;
    }
    public void showDetails() {
        System.out.println("BookService Setter Injection active: " + repository.getDbInfo());
    }
}
