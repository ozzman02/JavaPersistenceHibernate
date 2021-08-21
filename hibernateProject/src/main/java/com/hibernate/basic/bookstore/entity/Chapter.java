package com.hibernate.basic.bookstore.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Chapter {

    // Composite Primary Key
    @EmbeddedId
    private ChapterId chapterId;

    @ManyToOne
    @JoinColumn(name = "BOOK_ISBN")
    @MapsId("isbn")
    private Book book;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    public Chapter() {	}

    public Chapter(ChapterId chapterId, String title) {
        this.chapterId = chapterId;
        this.title = title;
    }

    public ChapterId getChapterId() {
        return chapterId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Chapter.class.getSimpleName() + "[", "]")
                .add("chapterId=" + chapterId)
                .add("title='" + title + "'")
                .toString();
    }
}
