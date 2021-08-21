package com.hibernate.advanced.hibernatejpaprovider.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @Transient
    private int age;

    public Message() {}

    public Message(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Message.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("text='" + text + "'")
                .toString();
    }
}
