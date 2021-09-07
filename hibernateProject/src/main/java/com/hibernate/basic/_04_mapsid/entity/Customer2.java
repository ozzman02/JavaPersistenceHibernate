package com.hibernate.basic._04_mapsid.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

@Entity
public class Customer2 {

	@Id
	private Long id;

	private String name;

	/* Here we want the customer id to the same as the passport id. We are borrowing the passport id */
	@OneToOne(cascade={CascadeType.PERSIST})
	@JoinColumn(name="passport_id", unique=true)
	@MapsId
	private Passport2 passport;
	
	public Customer2() {}

	public Customer2(String name, Passport2 passport) {
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

	public Passport2 getPassport() {
		return passport;
	}

	public void setPassport(Passport2 passport) {
		this.passport = passport;
	}

	@Override
	public String toString() {
		return "Customer2{" +
				"id=" + id +
				", name='" + name + '\'' +
				", passport=" + passport +
				'}';
	}
}











