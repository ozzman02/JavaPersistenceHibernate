package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.Entity;

@Entity
public class TablePerClassDog extends TablePerClassAnimal {

    @Override
    public String makeNoise() {
        return "woof woof ...";
    }

}
