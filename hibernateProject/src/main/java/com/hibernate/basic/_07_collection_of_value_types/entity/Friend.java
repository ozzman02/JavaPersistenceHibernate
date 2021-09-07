package com.hibernate.basic._07_collection_of_value_types.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @ElementCollection
    @CollectionTable(name = "friend_nickname", joinColumns = @JoinColumn(name = "friend_id"))
    @Column(name = "nickname")
    private Collection<String> nickNames = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "friend_address", joinColumns = @JoinColumn(name = "friend_id"))
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "address_zipcode")),
            @AttributeOverride(name = "street", column = @Column(name = "address_street")),
            @AttributeOverride(name = "city", column = @Column(name = "address_city"))
    })
    private Collection<FriendAddress> addresses = new ArrayList<>();

    public Friend() {}
    public Friend(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<String> getNickNames() {
        return nickNames;
    }

    public void setNickNames(Collection<String> nickNames) {
        this.nickNames = nickNames;
    }

    public Collection<FriendAddress> getAddresses() {
        return addresses;
    }

    public void setAddresses(Collection<FriendAddress> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Friend.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("email='" + email + "'")
                .add("nickNames=" + nickNames)
                .add("addresses=" + addresses)
                .toString();
    }
}
