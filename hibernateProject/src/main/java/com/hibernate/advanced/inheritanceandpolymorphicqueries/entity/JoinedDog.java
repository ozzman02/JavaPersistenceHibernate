package com.hibernate.advanced.inheritanceandpolymorphicqueries.entity;

import javax.persistence.Entity;

@Entity
public class JoinedDog extends JoinedAnimal {

    @Override
    public String makeNoise() {
        return "woof woof ...";
    }

}
