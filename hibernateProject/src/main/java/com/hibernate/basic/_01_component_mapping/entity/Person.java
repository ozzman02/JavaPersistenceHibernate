package com.hibernate.basic._01_component_mapping.entity;

import javax.persistence.*;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String name;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="home_street")),
            @AttributeOverride(name="city", column=@Column(name="home_city")),
            @AttributeOverride(name="zipcode", column=@Column(name="home_zipcode"))
    } )
    private Address homeAddress;

    @Embedded
    @AttributeOverrides( {
            @AttributeOverride(name="street", column=@Column(name="billing_street")),
            @AttributeOverride(name="city", column=@Column(name="billing_city")),
            @AttributeOverride(name="zipcode", column=@Column(name="billing_zipcode"))
    } )
    private Address billingAddress;

    public Person() {}

    public Person(String name, Address homeAddress, Address billingAddress) {
        this.name = name;
        this.homeAddress = homeAddress;
        this.billingAddress = billingAddress;
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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

}
