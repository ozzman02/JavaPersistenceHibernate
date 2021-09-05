package com.hibernate.advanced.inheritanceandpolymorphicqueries.entity;

import javax.persistence.Entity;

@Entity
public class JoinedCat extends JoinedAnimal {

    @Override
    public String makeNoise() {
        return "meow meow ...";
    }

}
