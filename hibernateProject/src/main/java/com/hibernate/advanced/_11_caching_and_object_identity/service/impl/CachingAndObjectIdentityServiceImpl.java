package com.hibernate.advanced._11_caching_and_object_identity.service.impl;

import com.hibernate.advanced._11_caching_and_object_identity.entity.GuideForCachingAndObjectIdentity;
import com.hibernate.advanced._11_caching_and_object_identity.entity.StudentForCachingAndObjectIdentity;
import com.hibernate.advanced._11_caching_and_object_identity.service.CachingAndObjectIdentityService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.hibernate.advanced._13_constants.Constants.*;

public class CachingAndObjectIdentityServiceImpl implements CachingAndObjectIdentityService {

    @Override
    public void loadGuidesAndStudents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideForCachingAndObjectIdentity guide1 = new GuideForCachingAndObjectIdentity("2000MO10789", "Mike Lawson", 1000);
            GuideForCachingAndObjectIdentity guide2 = new GuideForCachingAndObjectIdentity("2000IM10901", "Ian Lamb", 2500);
            GuideForCachingAndObjectIdentity guide3 = new GuideForCachingAndObjectIdentity("2000DO10777", "David Crow", 3000);
            StudentForCachingAndObjectIdentity student1 = new StudentForCachingAndObjectIdentity("2014AL50456","Amy Gill");
            StudentForCachingAndObjectIdentity student2 = new StudentForCachingAndObjectIdentity("2014JT50123","John Smith");
            StudentForCachingAndObjectIdentity student3 = new StudentForCachingAndObjectIdentity("2014BE50789","Bruce Lee");
            StudentForCachingAndObjectIdentity student4 = new StudentForCachingAndObjectIdentity("2014RG50347","Rahul Singh");
            Set<StudentForCachingAndObjectIdentity> students = new LinkedHashSet<>();
            students.add(student1);
            students.add(student2);
            guide2.addStudents(students);
            guide3.addStudent(student4);
            entityManager.persist(guide1);
            entityManager.persist(guide2);
            entityManager.persist(student3);
            entityManager.persist(guide3);
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

    /*
        In this example only two select statements are issued

            select
                guideforca0_.id as id1_19_0_,
                guideforca0_.name as name2_19_0_,
                guideforca0_.salary as salary3_19_0_,
                guideforca0_.staff_id as staff_id4_19_0_,
                guideforca0_.version as version5_19_0_
            from
                GuideForCachingAndObjectIdentity guideforca0_
            where
                guideforca0_.id=?

            select
                guideforca0_.id as id1_19_,
                guideforca0_.name as name2_19_,
                guideforca0_.salary as salary3_19_,
                guideforca0_.staff_id as staff_id4_19_,
                guideforca0_.version as version5_19_
            from
                GuideForCachingAndObjectIdentity guideforca0_
            where
                guideforca0_.id=?
    */
    @Override
    public void cachingAndObjectIdentityExample() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        /* First level cache */
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            /* Hibernate is going to look for the object in the first level cache. If not found it goes to the database issuing a select statement */
            GuideForCachingAndObjectIdentity guide1 = entityManager.find(GuideForCachingAndObjectIdentity.class, 2L);

            /* Guide is found in the first level cache, no need of a select statement */
            GuideForCachingAndObjectIdentity guide2 = entityManager.find(GuideForCachingAndObjectIdentity.class, 2L);

            /*
                Here a select statement is issued and the reason is that a cache is id based.
                When you ask Hibernate to load an object using a JPQL query it does not know about the id of the object.
                Hibernate itself cannot execute a query, it could only generate a SQL at runtime, which is then executed in the database.
                By just looking at the query Hibernate cannot tell if it has the object in the first level cache.
                When the select statement is executed only then the entity manager finds that it has the id already in the first level cache
                that is why it does not create a new instance of the object.

            */
            GuideForCachingAndObjectIdentity guide3 = (GuideForCachingAndObjectIdentity) entityManager
                    .createQuery(FIND_GUIDE_BY_ID_QUERY).setParameter("id", 2L).getSingleResult();

            entityTransaction.commit();
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    /*
        In this example three select statements are issued

            select
                guideforca0_.id as id1_19_0_,
                guideforca0_.name as name2_19_0_,
                guideforca0_.salary as salary3_19_0_,
                guideforca0_.staff_id as staff_id4_19_0_,
                guideforca0_.version as version5_19_0_
            from
                GuideForCachingAndObjectIdentity guideforca0_
            where
                guideforca0_.id=?

            select
                guideforca0_.id as id1_19_,
                guideforca0_.name as name2_19_,
                guideforca0_.salary as salary3_19_,
                guideforca0_.staff_id as staff_id4_19_,
                guideforca0_.version as version5_19_
            from
                GuideForCachingAndObjectIdentity guideforca0_
            where
                guideforca0_.id=?

            select
                guideforca0_.id as id1_19_,
                guideforca0_.name as name2_19_,
                guideforca0_.salary as salary3_19_,
                guideforca0_.staff_id as staff_id4_19_,
                guideforca0_.version as version5_19_
            from
                GuideForCachingAndObjectIdentity guideforca0_
            where
                guideforca0_.name=?
    */
    @Override
    public void cachingAndObjectIdentityExample2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            /* select statement is issued */
            GuideForCachingAndObjectIdentity guide1 = entityManager.find(GuideForCachingAndObjectIdentity.class, 2L);

            /* select statement is issued */
            GuideForCachingAndObjectIdentity guide2 = (GuideForCachingAndObjectIdentity) entityManager
                    .createQuery(FIND_GUIDE_BY_ID_QUERY).setParameter(GUIDE_ID, 2L).getSingleResult();

            /* select statement is issued */
            GuideForCachingAndObjectIdentity guide3 = (GuideForCachingAndObjectIdentity) entityManager
                    .createQuery(FIND_GUIDE_BY_NAME_QUERY).setParameter(GUIDE_NAME, "Ian Lamb").getSingleResult();

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
