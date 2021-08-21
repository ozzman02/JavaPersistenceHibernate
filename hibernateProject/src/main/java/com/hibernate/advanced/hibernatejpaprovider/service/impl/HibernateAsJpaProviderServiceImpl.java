package com.hibernate.advanced.hibernatejpaprovider.service.impl;

import static com.hibernate.advanced.constants.Constants.PERSISTENCE_UNIT_NAME;

import com.hibernate.advanced.hibernatejpaprovider.entity.Message;
import com.hibernate.advanced.hibernatejpaprovider.service.HibernateAsJpaProviderService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class HibernateAsJpaProviderServiceImpl implements HibernateAsJpaProviderService {

    @Override
    public void saveMessage() {
        EntityManagerFactory entityManagerFactory
                = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Message msg = new Message("Hello World with Hibernate as JPA Provider"); // transient state
            entityManager.persist(msg); // persistence state
            entityTransaction.commit();
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

}
