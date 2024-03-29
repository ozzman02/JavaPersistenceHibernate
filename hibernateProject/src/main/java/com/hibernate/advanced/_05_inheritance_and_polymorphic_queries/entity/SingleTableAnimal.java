package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class SingleTableAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
