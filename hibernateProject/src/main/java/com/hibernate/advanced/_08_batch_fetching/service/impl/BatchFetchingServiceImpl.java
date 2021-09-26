package com.hibernate.advanced._08_batch_fetching.service.impl;

import com.hibernate.advanced._08_batch_fetching.entity.GuideObjectBatch;
import com.hibernate.advanced._08_batch_fetching.entity.StudentObjectBatch;
import com.hibernate.advanced._08_batch_fetching.service.BatchFetchingService;

import javax.persistence.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.hibernate.advanced._13_constants.Constants.PERSISTENCE_UNIT_NAME;

public class BatchFetchingServiceImpl implements BatchFetchingService {

    @Override
    public void createStudentsAndGuides() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideObjectBatch guide1 = new GuideObjectBatch("2000MO10789", "Mike Lawson", 1000);
            GuideObjectBatch guide2 = new GuideObjectBatch("2000IM10901", "Ian Lamb", 2000);
            GuideObjectBatch guide3 = new GuideObjectBatch("2000DO10777", "David Crow", 3000);
            StudentObjectBatch student1 = new StudentObjectBatch("2014AL50456","Amy Gill");
            StudentObjectBatch student2 = new StudentObjectBatch("2014JT50123","John Smith");
            StudentObjectBatch student3 = new StudentObjectBatch("2014BE50789","Bruce Lee");
            StudentObjectBatch student4 = new StudentObjectBatch("2014RG50347","Rahul Singh");
            Set<StudentObjectBatch> students = new LinkedHashSet<>();
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
        Loading all the students lazily; the guides associated with students are "not" being accessed

            select
                studentobj0_.id as id1_38_,
                studentobj0_.enrollment_id as enrollme2_38_,
                studentobj0_.guide_id as guide_id4_38_,
                studentobj0_.name as name3_38_
            from
                StudentObjectBatch studentobj0_

        Loading all the students lazily using @BatchSize(size=2); the guides associated with students are being accessed

            select
                studentobj0_.id as id1_38_,
                studentobj0_.enrollment_id as enrollme2_38_,
                studentobj0_.guide_id as guide_id4_38_,
                studentobj0_.name as name3_38_
            from
                StudentObjectBatch studentobj0_

            select
                guideobjec0_.id as id1_19_0_,
                guideobjec0_.name as name2_19_0_,
                guideobjec0_.salary as salary3_19_0_,
                guideobjec0_.staff_id as staff_id4_19_0_
            from
                GuideObjectBatch guideobjec0_
            where
                guideobjec0_.id in (?, ?)
    */
    @Override
    public void displayStudentInfo() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
			Query query = entityManager.createQuery("select student from StudentObjectBatch student");
			List<StudentObjectBatch> students = query.getResultList();
			for (StudentObjectBatch student : students) {
                if (student.getGuide() != null) {
                    System.out.println(student.getName() + ": " + student.getEnrollmentId() + ": " + student.getGuide().getName());
                }
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

}
