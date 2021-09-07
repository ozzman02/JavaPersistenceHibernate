package com.hibernate.basic._07_collection_of_value_types.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.StringJoiner;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(name = "item_image", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "filename")
    private Collection<String> images = new ArrayList<>();

    public Item() {
    }

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<String> getImages() {
        return images;
    }

    public void setImages(Collection<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Item.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("images=" + images)
                .toString();
    }
}
