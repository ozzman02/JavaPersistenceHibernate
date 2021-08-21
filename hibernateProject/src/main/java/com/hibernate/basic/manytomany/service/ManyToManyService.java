package com.hibernate.basic.manytomany.service;

public interface ManyToManyService {

    void clearMoviesAndActors();

    void saveMoviesWithActors();

    void updateMovie();

    void updateActor();

    void deleteMovieFromActor();

}
