package com.hibernate.advanced.inheritanceandpolymorphicqueries.entity;

import javax.persistence.Entity;

@Entity
public class SingleTableCat extends SingleTableAnimal {

    @Override
    public String makeNoise() {
        return "meow meow ...";
    }

}
