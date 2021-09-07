package com.hibernate.basic._03_one_to_one.entity;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /* owner of the relationship */
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "passport_id", unique = true)
    private Passport passport;

    public Customer() {
    }

    public Customer(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Passport getPassport() {
        return passport;
    }

    public void setPassport(Passport passport) {
        this.passport = passport;
    }

}
