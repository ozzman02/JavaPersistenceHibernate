package com.hibernate.basic.bookstore.client;

import com.hibernate.basic.bookstore.service.BookStoreService;
import com.hibernate.basic.bookstore.service.impl.BookStoreServiceImpl;

public class BookStoreClient {
    public static void main(String[] args) {
        BookStoreService bookStoreService = new BookStoreServiceImpl();
        bookStoreService.saveBook();
        bookStoreService.getBook();
    }
}
