package com.hibernate.basic._09_bookstore.client;

import com.hibernate.basic._09_bookstore.service.BookStoreService;
import com.hibernate.basic._09_bookstore.service.impl.BookStoreServiceImpl;

public class BookStoreClient {
    public static void main(String[] args) {
        BookStoreService bookStoreService = new BookStoreServiceImpl();
        bookStoreService.saveBook();
        bookStoreService.getBook();
    }
}
