package com.hibernate.advanced.lazyandeagerfetching.service.impl;

import com.hibernate.advanced.lazyandeagerfetching.entity.Guide2;
import com.hibernate.advanced.lazyandeagerfetching.entity.Student2;
import com.hibernate.advanced.lazyandeagerfetching.service.LazyAndEagerFetchingService;
import com.hibernate.basic.onetomany.entity.Guide;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.util.Set;

import static com.hibernate.advanced.constants.Constants.PERSISTENCE_UNIT_NAME;

public class LazyAndEagerFetchingServiceImpl implements LazyAndEagerFetchingService {

    @Override
    public void saveGuideAndStudents() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Guide2 guide1 = new Guide2("2000MO10789", "Mike Lawson", 1000);
            Guide2 guide2 = new Guide2("2000IM10901", "Ian Lamb", 2000);
            Guide2 guide3 = new Guide2("2000IM10888", "Dean Ring", 2000);
            Student2 student1 = new Student2("2014JT50123", "John Smith", guide1);
            Student2 student2 = new Student2("2014AL50456", "Amy Gill", guide1);
            Student2 student3 = new Student2("2014AL50333", "Oscar Santamaria", guide2);
            Student2 student4 = new Student2("2014AL50222", "John Lopez", guide3);
            guide1.addStudent(student1);
            guide1.addStudent(student2);
            guide2.addStudent(student3);
            guide3.addStudent(student4);
            entityManager.persist(guide1);
            entityManager.persist(guide2);
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

    @Override
    public void getGuide() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            // Lazy Collection Fetching with default settings(fetch=FetchType.LAZY for collection associations)
            Guide2 guide = entityManager.find(Guide2.class, 1L);
            // at this point there is no select statement to get the students
            Set<Student2> students = guide.getStudents();
            // proxy gets initialized with methods like size or contains or with a for loop
            int numberOfStudents = students.size();
            for (Student2 student : students) {
                System.out.println(student);
            }
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
    public void getStudent() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            //Eager Fetching with default settings (fetch=FetchType.EAGER for single point associations)
            Student2 student = entityManager.find(Student2.class, 2L);
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
    public void equalsAndHasCode() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            // Student object is loaded from the database. ManyToOne by default is eager fetch
            // therefore it loads the associated guide as well.
            Student2 student = entityManager.find(Student2.class, 2L);
            // The guide gets a proxy object for its student collections because OneToMany by default
            // is lazy fetching.
            Guide2 guide = entityManager.find(Guide2.class, 1L);
            // At this point student and guide objects are managed by the EntityManager that works as
            // first level cache. No database operation is going to take place.
            Set<Student2> students = guide.getStudents();
            // Both student and students collection have a reference to the same Student object with id 2.
            // The result is true because we have only one Entity Manager. It will be false if we handle
            // the student in one entity manager and the guide in another entity manager.
            System.out.println(students.contains(student));
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
