package com.hibernate.basic._05_many_to_many.service;

public interface ManyToManyService {

    void clearMoviesAndActors();

    void saveMoviesWithActors();

    void updateMovie();

    void updateActor();

    void deleteMovieFromActor();

}
