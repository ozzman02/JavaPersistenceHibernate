package com.hibernate.basic._09_bookstore.service.impl;

import com.hibernate.basic._09_bookstore.entity.Book;
import com.hibernate.basic._09_bookstore.entity.Chapter;
import com.hibernate.basic._09_bookstore.entity.ChapterId;
import com.hibernate.basic._09_bookstore.entity.Publisher;
import com.hibernate.basic._09_bookstore.service.BookStoreService;
import com.hibernate.basic._10_util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class BookStoreServiceImpl implements BookStoreService {

    @Override
    public void saveBook() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Publisher publisher = new Publisher("OREI", "O'Reilly Media, Inc.");
            Book book = new Book("9781449334376", "Just Hibernate", publisher);
            ChapterId chapter1Id = new ChapterId(book.getIsbn(), 1);
            Chapter chapter1 = new Chapter(chapter1Id, "Basics");
            book.addChapter(chapter1);
            //"null" won't make any difference as "isbn" in ChapterId will be ignored by Hibernate
            ChapterId chapter2Id = new ChapterId(null, 2);
            Chapter chapter2 = new Chapter(chapter2Id, "Fundamentals");
            book.addChapter(chapter2);
            session.persist(book);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void getBook() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Book book = session.get(Book.class, "9781449334376");
            System.out.println(book);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
