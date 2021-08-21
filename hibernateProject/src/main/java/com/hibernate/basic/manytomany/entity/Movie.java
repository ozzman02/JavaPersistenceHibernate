package com.hibernate.basic.manytomany.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    /* update of the relationship */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "movie_actor",
            joinColumns = {@JoinColumn(name="movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private Set<Actor> actors = new HashSet<>();

    public Movie() {
    }

    public Movie(String name) {
        this.name = name;
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

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }

}
