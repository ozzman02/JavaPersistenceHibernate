package com.hibernate.advanced._09_merge_detached_objects.service.impl;

import com.hibernate.advanced._07_selects_problem.entity.GuideObject;
import com.hibernate.advanced._07_selects_problem.entity.StudentObject;
import com.hibernate.advanced._09_merge_detached_objects.entity.GuideForMergedDetachedObj;
import com.hibernate.advanced._09_merge_detached_objects.entity.StudentForMergedDetachedObj;
import com.hibernate.advanced._09_merge_detached_objects.service.MergeDetachedObjectsService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.hibernate.advanced.constants.Constants.PERSISTENCE_UNIT_NAME;

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

}
