package com.hibernate.advanced._09_merge_detached_objects.service.impl;

import com.hibernate.advanced._09_merge_detached_objects.entity.*;
import com.hibernate.advanced._09_merge_detached_objects.service.MergeDetachedObjectsService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.hibernate.advanced._13_constants.Constants.PERSISTENCE_UNIT_NAME;

public class MergeDetachedObjectsServiceImpl implements MergeDetachedObjectsService {

    @Override
    public void createGuidesAndStudents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideForMergedDetachedObj guide1 = new GuideForMergedDetachedObj("2000MO10789", "Mike Lawson", 1000);
            GuideForMergedDetachedObj guide2 = new GuideForMergedDetachedObj("2000IM10901", "Ian Lamb", 2000);
            GuideForMergedDetachedObj guide3 = new GuideForMergedDetachedObj("2000DO10777", "David Crow", 3000);
            StudentForMergedDetachedObj student1 = new StudentForMergedDetachedObj("2014AL50456","Amy Gill");
            StudentForMergedDetachedObj student2 = new StudentForMergedDetachedObj("2014JT50123","John Smith");
            StudentForMergedDetachedObj student3 = new StudentForMergedDetachedObj("2014BE50789","Bruce Lee");
            StudentForMergedDetachedObj student4 = new StudentForMergedDetachedObj("2014RG50347","Rahul Singh");
            Set<StudentForMergedDetachedObj> students = new LinkedHashSet<>();
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

    /* Loading, Modifying and Storing/Persisting data using Detached Objects */
    @Override
    public void mergingDetachedObjects() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();

            /* Load a guide with id 2 that comes with a students proxy */
            GuideForMergedDetachedObj guide = entityManager.find(GuideForMergedDetachedObj.class, 2L);
            Set<StudentForMergedDetachedObj> students = guide.getStudents();

            /* Initialize the collection of students */
            int numberOfStudents = students.size();

            StudentForMergedDetachedObj student = null;
            for (StudentForMergedDetachedObj nextStudent : students) {
                if (nextStudent.getId() == 2L) {
                    student = nextStudent;
                }
            }

            /* Underlying JDBC connection will get disconnected */
            entityTransaction.commit();

            /* After closing the entity manager all objects become detached from the persistence context */
            entityManager.close();

            /* Modifying the detached objects */
            guide.setSalary(2500);
            student.setName("Amy Jade Gill");

            /* Merging Detached Object */
            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            entityManager2.getTransaction().begin();

            /*
                entityManager2.merge(guide)

                    - Checks if a guide with id 2 is already being managed by the new persistence context.
                    - When it does not find it, it will issue a request to the database.
                    - The request gets the student collection initialized and loaded, the guide with id 2 is managed by the new persistence context.
                    - At this point, the JPA persistence engine will copy the state of the detached guide into the mergedGuide.
                    - When that happens only the salary is updated but to update the student name CascadeType.Merge must be enabled in the Guide entity.
            */
            GuideForMergedDetachedObj mergedGuide = entityManager2.merge(guide);

            /*
                Dirty check process is completed.

                    update StudentForMergedDetachedObj set enrollment_id=?, guide_id=?, name=? where id=?
                    update GuideForMergedDetachedObj set name=?, salary=?, staff_id=? where id=?
            */
            entityManager2.getTransaction().commit();

            entityManager2.close();
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void createGuideAndStudentsForExtendedPersistenceContext() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideForExtendedPersistenceContext guide1 = new GuideForExtendedPersistenceContext("2000MO10789", "Mike Lawson", 1000);
            GuideForExtendedPersistenceContext guide2 = new GuideForExtendedPersistenceContext("2000IM10901", "Ian Lamb", 2000);
            GuideForExtendedPersistenceContext guide3 = new GuideForExtendedPersistenceContext("2000DO10777", "David Crow", 3000);
            StudentForExtendedPersistenceContext student1 = new StudentForExtendedPersistenceContext("2014AL50456","Amy Gill");
            StudentForExtendedPersistenceContext student2 = new StudentForExtendedPersistenceContext("2014JT50123","John Smith");
            StudentForExtendedPersistenceContext student3 = new StudentForExtendedPersistenceContext("2014BE50789","Bruce Lee");
            StudentForExtendedPersistenceContext student4 = new StudentForExtendedPersistenceContext("2014RG50347","Rahul Singh");
            Set<StudentForExtendedPersistenceContext> students = new LinkedHashSet<>();
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

    /* Loading, Modifying and Storing/Persisting data using Extended Persistence Context */
    @Override
    public void objectModificationsOnAnExtendedPersistenceContext() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideForExtendedPersistenceContext guide = entityManager.find(GuideForExtendedPersistenceContext.class, 2L);
            Set<StudentForExtendedPersistenceContext> students = guide.getStudents();
            int numberOfStudents = students.size();
            StudentForExtendedPersistenceContext student = null;
            for (StudentForExtendedPersistenceContext nextStudent : students) {
                if (nextStudent.getId() == 2L) {
                    student = nextStudent;
                }
            }

            /* Underlying JDBC connection will get disconnected */
            entityTransaction.commit();

            /* Modifications are done while there is no underlying JDBC Connection but the objects are still associated with a persistence context */
            guide.setSalary(2500);
            student.setName("Amy Jade Gill");

            /* merging not needed between entityManager.getTransaction().begin(); and entityManager.getTransaction().commit(); */
            entityManager.getTransaction().begin();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void createSimpleStudentData() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        SimpleStudent student1 = new SimpleStudent("2003CH50897", "Kevin Smith");
        SimpleStudent student2 = new SimpleStudent("2008EE10999", "Sherry Morgan");
        SimpleStudent student3 = new SimpleStudent("2009DS10954", "Rohit Sharma");
        SimpleStudent student4 = new SimpleStudent("1987CS10007", "Sara Shield");
        entityManager.persist(student1);
        entityManager.persist(student2);
        entityManager.persist(student3);
        entityManager.persist(student4);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /*
        Behavior without overriding the equals() method:

            - Object is assigned a memory id (Java identity), Student id is 2 (database identity)
                SimpleStudent student1 = entityManager.find(SimpleStudent.class, 2L);

            - Student id is 2 (database identity)
                SimpleStudent student2 = entityManager.find(SimpleStudent.class, 2L);

            - Both variables student1 and student2 are referencing the same object in java memory. Result is true
                System.out.println(student1 == student2);

            - By default, it relies on the Java identity. Result is true
                System.out.println(student1.equals(student2));

            - Database ids are the same. Result is true
                System.out.println(student1.getId().equals(student2.getId()));

            - Object become detached. We have only 1 object but two reference variables
                entityManager.getTransaction().commit();
                entityManager.close();

            - Create new persistence context
                EntityManager entityManager2 = entityManagerFactory.createEntityManager();
                entityManager2.getTransaction().begin();

            - Object will have different Java identity since we are using a new persistence context
                SimpleStudent student3 = entityManager2.find(SimpleStudent.class, 2L);

            - Java identities are different. Result is false
                System.out.println(student1 == student3);

            - We have a new object in a new persistence context. Result is false
                System.out.println(student1.equals(student3));

            - Database ids are the same. Result is true
                System.out.println(student1.getId().equals(student3.getId()));

            - Object become detached
                entityManager2.getTransaction().commit();
                entityManager2.close();

            - Set collection does not take duplicates. We have two different objects in a detached state. The result of size operation will be 2.
              By Default, it will use Java identity to store elements.

                Set<SimpleStudent> students = new HashSet<>();
                students.add(student1);
                students.add(student2);
                students.add(student3);
                System.out.println(students.size());
    */
    @Override
    public void identityOfDetachedObjects() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        SimpleStudent student1 = entityManager.find(SimpleStudent.class, 2L);
        SimpleStudent student2 = entityManager.find(SimpleStudent.class, 2L);

        System.out.println(student1 == student2);
        System.out.println(student1.equals(student2));
        System.out.println(student1.getId().equals(student2.getId()));

        entityManager.getTransaction().commit();
        entityManager.close();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();

        SimpleStudent student3 = entityManager2.find(SimpleStudent.class, 2L);

        System.out.println(student1 == student3);
        System.out.println(student1.equals(student3));
        System.out.println(student1.getId().equals(student3.getId()));

        entityManager2.getTransaction().commit();
        entityManager2.close();

        Set<SimpleStudent> students = new HashSet<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        System.out.println(students.size());
    }

    /*
        Do not use database identities for equality. We want to prevent a situation like this one but it can be fixed if we return a constant.

            @Override
            public int hasCode() {
                return 31;
            }

    */
    @Override
    public void usingIdForEqualsAndHashCode() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        OtherSimpleStudent student = new OtherSimpleStudent("2021EE10444", "Rob Winter");

        /* prints 31 */
        System.out.println(student.hashCode());

        Set<OtherSimpleStudent> students = new HashSet<>();

        /* student.getId() == null */
        students.add(student);

        /* student.getId() != null */
        entityManager.persist(student);

        /* prints 32 */
        System.out.println(students.iterator().next().hashCode());

        /* prints false, student changed buckets ---> student not found in bucket 31 anymore */
        System.out.println(students.contains(student));

        entityManager.getTransaction().commit();
        entityManager.close();
    }

}
