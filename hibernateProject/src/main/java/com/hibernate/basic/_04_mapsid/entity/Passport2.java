package com.hibernate.basic._04_mapsid.entity;

import javax.persistence.*;

@Entity
public class Passport2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="passport_number")
	private String passportNumber;

	public Passport2() {}

	public Passport2(String passportNumber) {
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

	@Override
	public String toString() {
		return "Passport2{" +
				"id=" + id +
				", passportNumber='" + passportNumber + '\'' +
				'}';
	}

}













