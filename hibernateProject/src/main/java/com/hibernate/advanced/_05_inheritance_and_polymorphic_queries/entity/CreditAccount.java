package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

/* Can't run polymorphic queries like "select account from Account account" */
@Entity
@Table(name="credit_account")
public class CreditAccount extends Account {

    @Column(name="credit_limit")
    private BigDecimal creditLimit;

    public void setCreditLimit(BigDecimal creditLimit) {
        this.creditLimit = creditLimit;
    }

}
