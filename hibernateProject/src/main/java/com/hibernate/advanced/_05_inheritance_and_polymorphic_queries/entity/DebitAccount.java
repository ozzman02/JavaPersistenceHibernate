package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name="debit_account")
public class DebitAccount extends Account {

    @Column(name="overdraft_fee")
    private BigDecimal overdraftFee;

    public void setOverdraftFee(BigDecimal overdraftFee) {
        this.overdraftFee = overdraftFee;
    }

}
