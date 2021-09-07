package com.hibernate.basic._09_bookstore.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class Book {

    @Id
    @Column(name = "ISBN", unique = true, nullable = false, length = 50)
    private String isbn;

    @Column(name = "BOOK_NAME", nullable = false, length = 100)
    private String name;

    @ManyToOne(cascade= {CascadeType.PERSIST})
    @JoinColumn(name = "PUBLISHER_CODE")
    private Publisher publisher;

    @OneToMany(mappedBy = "book", cascade= {CascadeType.PERSIST})
    private Set<Chapter> chapters = new HashSet<>();

    public Book() {}

    public Book(String isbn, String name) {
        this.isbn = isbn;
        this.name = name;
    }

    public Book(String isbn, String name, Publisher publisher) {
        this.isbn = isbn;
        this.name = name;
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Set<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(Set<Chapter> chapters) {
        this.chapters = chapters;
    }

    public void addChapter(Chapter chapter) {
        this.chapters.add(chapter);
        chapter.setBook(this);
    }

    public void removeChapter(Chapter chapter) {
        this.chapters.remove(chapter);
        chapter.setBook(null);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Book.class.getSimpleName() + "[", "]")
                .add("isbn='" + isbn + "'")
                .add("name='" + name + "'")
                .add("publisher=" + publisher)
                .add("chapters=" + chapters)
                .toString();
    }

}
