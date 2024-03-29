package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class JoinedAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public abstract String makeNoise();

    @Override
    public String toString() {
        return name + " making " + makeNoise() + " noises";
    }
}
