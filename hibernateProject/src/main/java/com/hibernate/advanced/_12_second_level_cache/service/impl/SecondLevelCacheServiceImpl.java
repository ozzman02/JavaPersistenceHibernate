package com.hibernate.advanced._12_second_level_cache.service.impl;

import com.hibernate.advanced._12_second_level_cache.entity.GuideForSecondLevelCache;
import com.hibernate.advanced._12_second_level_cache.entity.StudentForSecondLevelCache;
import com.hibernate.advanced._12_second_level_cache.service.SecondLevelCacheService;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.hibernate.advanced._13_constants.Constants.PERSISTENCE_UNIT_NAME;

public class SecondLevelCacheServiceImpl implements SecondLevelCacheService {

    @Override
    public void createGuideAndStudents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideForSecondLevelCache guide1 = new GuideForSecondLevelCache("2000MO10789", "Mike Lawson", 1000);
            GuideForSecondLevelCache guide2 = new GuideForSecondLevelCache("2000IM10901", "Ian Lamb", 2500);
            GuideForSecondLevelCache guide3 = new GuideForSecondLevelCache("2000DO10777", "David Crow", 3000);
            StudentForSecondLevelCache student1 = new StudentForSecondLevelCache("2014AL50456","Amy Gill");
            StudentForSecondLevelCache student2 = new StudentForSecondLevelCache("2014JT50123","John Smith");
            StudentForSecondLevelCache student3 = new StudentForSecondLevelCache("2014BE50789","Bruce Lee");
            StudentForSecondLevelCache student4 = new StudentForSecondLevelCache("2014RG50347","Rahul Singh");
            Set<StudentForSecondLevelCache> students = new LinkedHashSet<>();
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

    @Override
    public void secondLevelCachingExample() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();

        /* Is Guide[id=2] available in second-level cache? */
        System.out.println("Is Guide[id=2] available in second-level cache? " + entityManagerFactory.getCache().contains(GuideForSecondLevelCache.class, 2L));
        GuideForSecondLevelCache guide1 = entityManager1.find(GuideForSecondLevelCache.class, 2L);

        entityManager1.getTransaction().commit();
        entityManager1.close();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();

        /* Is Guide[id=2] available in second-level cache? */
        System.out.println("Is Guide[id=2] available in second-level cache? " + entityManagerFactory.getCache().contains(GuideForSecondLevelCache.class, 2L));
        GuideForSecondLevelCache guide2 = entityManager2.find(GuideForSecondLevelCache.class, 2L);

        entityManager2.getTransaction().commit();
        entityManager2.close();

    }

    @Override
    public void statisticsExample() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        Statistics statistics = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        statistics.setStatisticsEnabled(true);

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();

        GuideForSecondLevelCache guide1 = entityManager1.find(GuideForSecondLevelCache.class, 2L);

        entityManager1.getTransaction().commit();
        entityManager1.close();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();

        GuideForSecondLevelCache guide2 = entityManager2.find(GuideForSecondLevelCache.class, 2L);

        entityManager2.getTransaction().commit();
        entityManager2.close();

        System.out.println(statistics.getSecondLevelCacheStatistics("com.hibernate.advanced._12_second_level_cache.entity.GuideForSecondLevelCache"));
    }

    @Override
    public void cachingAssociations() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        Statistics statistics = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        statistics.setStatisticsEnabled(true);

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();

        StudentForSecondLevelCache student1 = entityManager1.find(StudentForSecondLevelCache.class, 4L);
        String guide1Name = student1.getGuide().getName();

        entityManager1.getTransaction().commit();
        entityManager1.close();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();

        StudentForSecondLevelCache student2 = entityManager2.find(StudentForSecondLevelCache.class, 4L);
        String guide2Name = student2.getGuide().getName();

        entityManager2.getTransaction().commit();
        entityManager2.close();

        System.out.println("Student: " + statistics.getSecondLevelCacheStatistics("com.hibernate.advanced._12_second_level_cache.entity.StudentForSecondLevelCache"));
        System.out.println("Guide: " + statistics.getSecondLevelCacheStatistics("com.hibernate.advanced._12_second_level_cache.entity.GuideForSecondLevelCache"));
    }

    @Override
    public void cachingCollections() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);

        Statistics statistics = entityManagerFactory.unwrap(SessionFactory.class).getStatistics();
        statistics.setStatisticsEnabled(true);

        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        entityManager1.getTransaction().begin();

        GuideForSecondLevelCache guide1 = entityManager1.find(GuideForSecondLevelCache.class, 2L);
        int size1 = guide1.getStudents().size();

        entityManager1.getTransaction().commit();
        entityManager1.close();

        EntityManager entityManager2 = entityManagerFactory.createEntityManager();
        entityManager2.getTransaction().begin();

        GuideForSecondLevelCache guide2 = entityManager2.find(GuideForSecondLevelCache.class, 2L);
        int size2 = guide2.getStudents().size();

        entityManager2.getTransaction().commit();
        entityManager2.close();

        System.out.println("Student: " + statistics.getSecondLevelCacheStatistics("com.hibernate.advanced._12_second_level_cache.entity.StudentForSecondLevelCache"));
        System.out.println("Guide: " + statistics.getSecondLevelCacheStatistics("com.hibernate.advanced._12_second_level_cache.entity.GuideForSecondLevelCache"));
    }

}
