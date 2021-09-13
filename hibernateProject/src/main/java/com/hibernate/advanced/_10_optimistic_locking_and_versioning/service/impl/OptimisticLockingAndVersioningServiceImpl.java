package com.hibernate.advanced._10_optimistic_locking_and_versioning.service.impl;

import com.hibernate.advanced._10_optimistic_locking_and_versioning.entity.GuideForOptimisticLocking;
import com.hibernate.advanced._10_optimistic_locking_and_versioning.entity.StudentForOptimisticLocking;
import com.hibernate.advanced._10_optimistic_locking_and_versioning.service.OptimisticLockingAndVersioningService;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.hibernate.advanced.constants.Constants.PERSISTENCE_UNIT_NAME;

public class OptimisticLockingAndVersioningServiceImpl implements OptimisticLockingAndVersioningService {

    @Override
    public void loadGuidesAndStudents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideForOptimisticLocking guide1 = new GuideForOptimisticLocking("2000MO10789", "Mike Lawson", 1000);
            GuideForOptimisticLocking guide2 = new GuideForOptimisticLocking("2000IM10901", "Ian Lamb", 2500);
            GuideForOptimisticLocking guide3 = new GuideForOptimisticLocking("2000DO10777", "David Crow", 3000);
            StudentForOptimisticLocking student1 = new StudentForOptimisticLocking("2014AL50456","Amy Gill");
            StudentForOptimisticLocking student2 = new StudentForOptimisticLocking("2014JT50123","John Smith");
            StudentForOptimisticLocking student3 = new StudentForOptimisticLocking("2014BE50789","Bruce Lee");
            StudentForOptimisticLocking student4 = new StudentForOptimisticLocking("2014RG50347","Rahul Singh");
            Set<StudentForOptimisticLocking> students = new LinkedHashSet<>();
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
        We are simulating two concurrent users trying to update the salary of guide with id 2 at the same time.
    */
    @Override
    public void updateGuideSalaryConcurrently() {
        user1Interaction();
    }

    /*
        Read Consistent Data with LockModeType.PESSIMISTIC_READ.
        In this conversation we want to get the sum of the salaries but another user might want to update the salary of a guide before
        getting the total sum of salaries.
    */
    @Override
    public void pessimisticReadLockExample() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<Object[]> resultList = entityManager.createQuery("select guide.name, guide.salary from GuideForOptimisticLocking as guide")
                    .setLockMode(LockModeType.PESSIMISTIC_READ)
                    .getResultList();
            for (Object[] objects : resultList) {
                System.out.println(Arrays.toString(objects));
            }

            /*
                Other user wants to update the salary of guide with id 3 to 4000. Since we are LockModeType.PESSIMISTIC_READ
                the update will throw an exception. The sum of salaries query will not be executed. Basically by adding a lock
                you do not want that nobody could modify the data in the database as long as you are done with the transaction.
            */
            //user3Interaction();

            long sumOfSalaries = (long) entityManager.createQuery("select sum(guide.salary) from GuideForOptimisticLocking as guide").getSingleResult();
            System.out.println("[sumOfSalaries: " + sumOfSalaries + "]");
            entityManager.getTransaction().commit();

        } catch (Exception ex) {
            System.out.println("Someone else is trying to perform an operation while you want to accomplish other task ...");
            ex.printStackTrace();
        } finally {
            entityManager.close();
        }

    }

    /*
        LockModeType.PESSIMISTIC_WRITE: It will not allow other transactions or users to even read the data that is being locked.
        LockModeType.PESSIMISTIC_READ: Other users will be able to read the data is being locked.
    */
    @Override
    public void pessimisticWriteLockExample() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        List<Object[]> resultList = entityManager.createQuery("select guide.name, guide.salary from GuideForOptimisticLocking as guide")
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();
        for (Object[] objects : resultList) {
            System.out.println(Arrays.toString(objects));
        }
        long sumOfSalaries = (long) entityManager.createQuery("select sum(guide.salary) from GuideForOptimisticLocking as guide").getSingleResult();
        System.out.println("[sumOfSalaries: " + sumOfSalaries + "]");

        /* The lock is released here. Here we are raising the guide salaries 4 times */
        entityManager.createQuery("update GuideForOptimisticLocking as guide set guide.salary = guide.salary * 4").executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    /*
        User 1 starts the update but in the middle of setting the new salary user2 tries to perform an update on the salary of the same guide.
        We are using the versioning strategy (optimistic locking) to prevent lost updates. In this case last commit does not win.
        Update of user2 is executed changing the version from 0 to 1. By that point, user1 is trying to update an older version and that's
        the reason for the exception.
    */
    private void user1Interaction() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        GuideForOptimisticLocking guide = entityManager1.find(GuideForOptimisticLocking.class, 2L);
        entityManager1.getTransaction().commit();
        entityManager1.close();
        user2Interaction();
        guide.setSalary(3000);
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        EntityTransaction txn2 = entityManager2.getTransaction();
        try {
            txn2.begin();
            GuideForOptimisticLocking mergedGuide = entityManager2.merge(guide);
            txn2.commit();
        } catch (OptimisticLockException ole) {
            txn2.rollback();
            System.err.println("The guide was updated by some other user while you were doing interesting things.");
            ole.printStackTrace();
        } finally {
            entityManager2.close();
        }
    }

    private void user2Interaction() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        GuideForOptimisticLocking guide = entityManager.find(GuideForOptimisticLocking.class, 2L);
        entityManager.getTransaction().commit();
        entityManager.close();
        guide.setSalary(4000);
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        GuideForOptimisticLocking mergedGuide = entityManager2.merge(guide);
        entityManager2.getTransaction().commit();
        entityManager2.close();
    }

    private void user3Interaction() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();
        GuideForOptimisticLocking guide = entityManager1.find(GuideForOptimisticLocking.class, 3L);
        entityManager1.getTransaction().commit();
        entityManager1.close();
        guide.setSalary(4000);
        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();
        GuideForOptimisticLocking mergedGuide = entityManager2.merge(guide);
        entityManager2.getTransaction().commit();
        entityManager2.close();
    }

}
