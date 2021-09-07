package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.Entity;

@Entity
public class SingleTableCat extends SingleTableAnimal {

    @Override
    public String makeNoise() {
        return "meow meow ...";
    }

}
