package com.hibernate.advanced.inheritanceandpolymorphicqueries.entity;

import javax.persistence.Entity;

@Entity
public class TablePerClassDog extends TablePerClassAnimal {

    @Override
    public String makeNoise() {
        return "woof woof ...";
    }

}
