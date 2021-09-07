package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;

@MappedSuperclass
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String owner;

    private BigDecimal balance;

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
