package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TablePerClassAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
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
