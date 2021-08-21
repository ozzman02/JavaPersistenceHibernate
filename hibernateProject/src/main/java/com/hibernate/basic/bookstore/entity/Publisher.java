package com.hibernate.basic.bookstore.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.StringJoiner;

@Entity
public class Publisher {

    @Id
    @Column(name = "CODE", unique = true, nullable = false, length = 4)
    private String code;

    @Column(name = "PUBLISHER_NAME", nullable = false, length = 100)
    private String name;

    public Publisher() {}

    public Publisher(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Publisher.class.getSimpleName() + "[", "]")
                .add("code='" + code + "'")
                .add("name='" + name + "'")
                .toString();
    }

}
