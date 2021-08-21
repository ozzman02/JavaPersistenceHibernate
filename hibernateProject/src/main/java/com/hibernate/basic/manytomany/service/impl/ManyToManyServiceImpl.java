package com.hibernate.basic.manytomany.service.impl;

import com.hibernate.basic.manytomany.entity.Actor;
import com.hibernate.basic.manytomany.entity.Movie;
import com.hibernate.basic.manytomany.service.ManyToManyService;
import com.hibernate.basic.util.DatabaseUtil;
import com.hibernate.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManyToManyServiceImpl implements ManyToManyService {

    @Override
    public void clearMoviesAndActors() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            DatabaseUtil.clear(session, Movie.class);
            DatabaseUtil.clear(session, Actor.class);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void saveMoviesWithActors() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Movie movie1 = new Movie("American Hustle");
            Movie movie2 = new Movie("The Prestige");
            Actor actor1 = new Actor("Christian Bale");
            Actor actor2 = new Actor("Hugh Jackman");
            movie1.getActors().add(actor1);
            movie2.getActors().add(actor1);
            movie2.getActors().add(actor2);
            session.persist(movie1);
            session.persist(movie2);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /*
        Updating inverse end:

        The inverse end will be ignored when updating the relationship values
        in the join table.

        This code will not work:

            movie = session.get(Movie.class, 1L);
            actor = session.get(Actor.class, 2L);
            actor.getMovies().add(movie);
    */
    @Override
    public void updateMovie() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Movie movie = session.get(Movie.class, 1L);
            Actor actor = session.get(Actor.class, 2L);
            movie.getActors().add(actor);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    /* By adding the addMovie method in Actor class the inverse end is also responsible
       for an update in the join table */
    @Override
    public void updateActor() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Movie movie = session.get(Movie.class, 1L);
            Actor actor = session.get(Actor.class, 2L);
            actor.addMovie(movie);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteMovieFromActor() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Movie movie = session.get(Movie.class, 1L);
            Actor actor = session.get(Actor.class, 2L);
            actor.removeMovie(movie);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
