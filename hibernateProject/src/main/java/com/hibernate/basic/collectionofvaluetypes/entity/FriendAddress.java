package com.hibernate.basic.collectionofvaluetypes.entity;

import javax.persistence.Embeddable;
import java.util.StringJoiner;

@Embeddable
public class FriendAddress {

    private String street;

    private String city;

    private String zipcode;

    public FriendAddress() {}

    public FriendAddress(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FriendAddress.class.getSimpleName() + "[", "]")
                .add("street='" + street + "'")
                .add("city='" + city + "'")
                .add("zipcode='" + zipcode + "'")
                .toString();
    }
}
