package com.hibernate.advanced.inheritanceandpolymorphicqueries.entity;

import javax.persistence.Entity;

@Entity
public class SingleTableDog extends SingleTableAnimal {

    @Override
    public String makeNoise() {
        return "woof woof ...";
    }

}
