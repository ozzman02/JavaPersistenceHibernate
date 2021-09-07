package com.hibernate.basic._08_composite_keys.primary.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /* Composite Foreign Key
       For a single column use @JoinColumn and for multiple columns use @JoinColumns */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="firstname_fk", referencedColumnName="firstname"),
            @JoinColumn(name="lastname_fk", referencedColumnName="lastname")
    })
    private Parent parent;

    public Child() {}

    public Child(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Child.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .toString();
    }
}
