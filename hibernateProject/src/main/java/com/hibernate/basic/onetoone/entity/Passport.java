package com.hibernate.basic.onetoone.entity;

import javax.persistence.*;

@Entity
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "passport_number")
    private String passportNumber;

    /* inverse end */
    @OneToOne(mappedBy = "passport")
    private Customer customer;

    public Passport() {
    }

    public Passport(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
