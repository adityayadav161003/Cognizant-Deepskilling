package com.library;

public class BookService {
    public void init() {
        System.out.println("Bean Lifecycle Init: Setting up resources.");
    }
    public void destroy() {
        System.out.println("Bean Lifecycle Destroy: Releasing connection channels.");
    }
}
