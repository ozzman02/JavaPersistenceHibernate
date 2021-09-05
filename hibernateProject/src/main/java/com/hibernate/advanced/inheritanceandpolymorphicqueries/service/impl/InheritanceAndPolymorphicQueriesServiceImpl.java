package com.hibernate.advanced.inheritanceandpolymorphicqueries.service.impl;

import com.hibernate.advanced.inheritanceandpolymorphicqueries.entity.*;
import com.hibernate.advanced.inheritanceandpolymorphicqueries.service.InheritanceAndPolymorphicQueriesService;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.List;

import static com.hibernate.advanced.constants.Constants.*;

public class InheritanceAndPolymorphicQueriesServiceImpl implements InheritanceAndPolymorphicQueriesService {

    @Override
    public void persistSingleTableAnimals() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            SingleTableCat cat = new SingleTableCat();
            cat.setName("Lucy");
            SingleTableDog dog = new SingleTableDog();
            dog.setName("Oliver");
            entityManager.persist(cat);
            entityManager.persist(dog);
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

    @Override
    public void singleTableStrategyPolymorphicQuery() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(SINGLE_TABLE_STRATEGY_ANIMALS_POLYMORPHIC_QUERY);
            List<SingleTableAnimal> animals = query.getResultList();
            animals.forEach(System.out::println);
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

    @Override
    public void displaySingleTableDogs() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(SINGLE_TABLE_STRATEGY_DOGS_POLYMORPHIC_QUERY);
            List<SingleTableDog> dogs = query.getResultList();
            dogs.forEach(System.out::println);
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

    @Override
    public void persistJoinedAnimals() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            JoinedCat cat = new JoinedCat();
            cat.setName("Lucy");
            JoinedDog dog = new JoinedDog();
            dog.setName("Oliver");
            entityManager.persist(cat);
            entityManager.persist(dog);
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

    @Override
    public void joinedStrategyPolymorphicQuery() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(JOINED_STRATEGY_ANIMALS_POLYMORPHIC_QUERY);
            List<JoinedAnimal> animals = query.getResultList();
            animals.forEach(System.out::println);
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

    @Override
    public void displayJoinedDogs() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(JOINED_STRATEGY_DOGS_POLYMORPHIC_QUERY);
            List<JoinedDog> dogs = query.getResultList();
            dogs.forEach(System.out::println);
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

    @Override
    public void persistTablePerClassAnimals() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            TablePerClassCat cat = new TablePerClassCat();
            cat.setName("Lucy");
            TablePerClassDog dog = new TablePerClassDog();
            dog.setName("Oliver");
            entityManager.persist(cat);
            entityManager.persist(dog);
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

    @Override
    public void tablePerClassStrategyPolymorphicQuery() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(TABLE_PER_CLASS_STRATEGY_ANIMALS_POLYMORPHIC_QUERY);
            List<TablePerClassAnimal> animals = query.getResultList();
            animals.forEach(System.out::println);
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

    @Override
    public void displayTablePerClassDogs() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(TABLE_PER_CLASS_STRATEGY_DOGS_POLYMORPHIC_QUERY);
            List<TablePerClassDog> dogs = query.getResultList();
            dogs.forEach(System.out::println);
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

    @Override
    public void persistAccounts() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            DebitAccount debitAccount1 = new DebitAccount();
            debitAccount1.setOwner( "John Doe" );
            debitAccount1.setBalance( BigDecimal.valueOf( 100 ) );
            debitAccount1.setOverdraftFee( BigDecimal.valueOf( 25 ) );

            CreditAccount creditAccount1 = new CreditAccount();
            creditAccount1.setOwner( "John Doe" );
            creditAccount1.setBalance( BigDecimal.valueOf( 1000 ) );
            creditAccount1.setCreditLimit( BigDecimal.valueOf( 5000 ) );

            DebitAccount debitAccount2 = new DebitAccount();
            debitAccount2.setOwner( "Jane Roe" );
            debitAccount2.setBalance( BigDecimal.valueOf( 200 ) );
            debitAccount2.setOverdraftFee( BigDecimal.valueOf( 25 ) );

            CreditAccount creditAccount2 = new CreditAccount();
            creditAccount2.setOwner( "Jane Roe" );
            creditAccount2.setBalance( BigDecimal.valueOf( 2000 ) );
            creditAccount2.setCreditLimit( BigDecimal.valueOf( 7000 ) );

            entityManager.persist( debitAccount1 );
            entityManager.persist( creditAccount1 );
            entityManager.persist( debitAccount2 );
            entityManager.persist( creditAccount2 );

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
