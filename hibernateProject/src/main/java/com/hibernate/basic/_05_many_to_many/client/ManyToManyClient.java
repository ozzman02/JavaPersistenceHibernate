package com.hibernate.basic._05_many_to_many.client;

import com.hibernate.basic._05_many_to_many.service.ManyToManyService;
import com.hibernate.basic._05_many_to_many.service.impl.ManyToManyServiceImpl;

public class ManyToManyClient {
    public static void main(String[] args) {
        ManyToManyService manyToManyService = new ManyToManyServiceImpl();
        manyToManyService.clearMoviesAndActors();
        manyToManyService.saveMoviesWithActors();
        manyToManyService.updateMovie();
        manyToManyService.updateActor();
        manyToManyService.deleteMovieFromActor();
    }
}
