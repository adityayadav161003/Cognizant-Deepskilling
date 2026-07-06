package com.library;

public class BookService {
    private BookRepository repository;
    public BookService(BookRepository repo) {
        this.repository = repo;
    }
    public void showDetails() {
        System.out.println("BookService Active: " + repository.getDbInfo());
    }
}
