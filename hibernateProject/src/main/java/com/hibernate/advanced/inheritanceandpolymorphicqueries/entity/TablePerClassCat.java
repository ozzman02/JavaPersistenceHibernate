package com.hibernate.advanced.inheritanceandpolymorphicqueries.entity;

import javax.persistence.Entity;

@Entity
public class TablePerClassCat extends TablePerClassAnimal {

    @Override
    public String makeNoise() {
        return "meow meow ...";
    }

}
