package com.hibernate.basic.manytomany.client;

import com.hibernate.basic.manytomany.service.ManyToManyService;
import com.hibernate.basic.manytomany.service.impl.ManyToManyServiceImpl;

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
