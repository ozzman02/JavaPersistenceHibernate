package com.hibernate.advanced._07_selects_problem.service.impl;

import com.hibernate.advanced._07_selects_problem.entity.GuideObject;
import com.hibernate.advanced._07_selects_problem.entity.StudentObject;
import com.hibernate.advanced._07_selects_problem.service.SelectsProblemService;

import javax.persistence.*;

import java.util.List;

import static com.hibernate.advanced.constants.Constants.PERSISTENCE_UNIT_NAME;

public class SelectsProblemServiceImpl implements SelectsProblemService {

    @Override
    public void createGuidesAndStudents() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideObject guide1 = new GuideObject("2000MO10789", "Mike Lawson", 1000);
            GuideObject guide2 = new GuideObject("2000IM10901", "Ian Lamb", 2000);
            GuideObject guide3 = new GuideObject("2000DO10777", "David Crow", 3000);
            StudentObject student1 = new StudentObject("2014AL50456","Amy Gill");
            StudentObject student2 = new StudentObject("2014JT50123","John Smith");
            StudentObject student3 = new StudentObject("2014BE50789","Bruce Lee");
            StudentObject student4 = new StudentObject("2014RG50347","Rahul Singh");
            guide2.addStudent(student1);
            guide2.addStudent(student2);
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
        Query: select student from StudentObject student

            N+1 Selects (3 selects)

            1. All we needed is student data

                select
                    studentobj0_.id as id1_36_,
                    studentobj0_.enrollment_id as enrollme2_36_,
                    studentobj0_.guide_id as guide_id4_36_,
                    studentobj0_.name as name3_36_
                from
                    Student_Object studentobj0_

            2. This one is getting the guide with id 2

                select
                    guideobjec0_.id as id1_18_0_,
                    guideobjec0_.name as name2_18_0_,
                    guideobjec0_.salary as salary3_18_0_,
                    guideobjec0_.staff_id as staff_id4_18_0_
                from
                    Guide_Object guideobjec0_
                where
                    guideobjec0_.id=?

                TRACE BasicBinder:64 - binding parameter [1] as [BIGINT] - [2]

            3. This one is getting the guide with id 3

                select
                    guideobjec0_.id as id1_18_0_,
                    guideobjec0_.name as name2_18_0_,
                    guideobjec0_.salary as salary3_18_0_,
                    guideobjec0_.staff_id as staff_id4_18_0_
                from
                    Guide_Object guideobjec0_
                where
                    guideobjec0_.id=?

                TRACE BasicBinder:64 - binding parameter [1] as [BIGINT] - [3]

            This is happening because by default single point associations (@OneToOne and @ManyToOne) are eagerly fetched.
            By default, collection associations (@OneToMany and @ManyToMany) are lazily fetched.

            The solution in this case is to set the fetch type of the @ManyToOne association to LAZY.

                select
                    studentobj0_.id as id1_36_,
                    studentobj0_.enrollment_id as enrollme2_36_,
                    studentobj0_.guide_id as guide_id4_36_,
                    studentobj0_.name as name3_36_
                from
                    Student_Object studentobj0_
     */
    @Override
    public void displayStudentNameAndEnrollmentId() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery("select student from StudentObject student");
            List<StudentObject> students = query.getResultList();
            for (StudentObject studentObject : students) {
                System.out.println(studentObject.getName() + " : " + studentObject.getEnrollmentId());
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

    /*
         Just because we change the fetching strategy of our @ManyToOne association does not mean that the N+1 select problem will never come back.
         In this example we are printing the guide name, and we can see that N+1 select problem is back.

             select
                studentobj0_.id as id1_36_,
                studentobj0_.enrollment_id as enrollme2_36_,
                studentobj0_.guide_id as guide_id4_36_,
                studentobj0_.name as name3_36_
            from
                Student_Object studentobj0_

            select
                guideobjec0_.id as id1_18_0_,
                guideobjec0_.name as name2_18_0_,
                guideobjec0_.salary as salary3_18_0_,
                guideobjec0_.staff_id as staff_id4_18_0_
            from
                Guide_Object guideobjec0_
            where
                guideobjec0_.id=?

                binding parameter [1] as [BIGINT] - [2]
                    Amy Gill : 2014AL50456 : Ian Lamb
                    John Smith : 2014JT50123 : Ian Lamb

            select
                guideobjec0_.id as id1_18_0_,
                guideobjec0_.name as name2_18_0_,
                guideobjec0_.salary as salary3_18_0_,
                guideobjec0_.staff_id as staff_id4_18_0_
            from
                Guide_Object guideobjec0_
            where
                guideobjec0_.id=?

                binding parameter [1] as [BIGINT] - [3]
                    Rahul Singh : 2014RG50347 : David Crow

        In this case we need to change JPQL query from "select student from StudentObject student" to
        "select student from StudentObject student left join fetch student.guide" to eagerly load the associated guide object
        while loading all the students.

            select
                studentobj0_.id as id1_36_0_,
                guideobjec1_.id as id1_18_1_,
                studentobj0_.enrollment_id as enrollme2_36_0_,
                studentobj0_.guide_id as guide_id4_36_0_,
                studentobj0_.name as name3_36_0_,
                guideobjec1_.name as name2_18_1_,
                guideobjec1_.salary as salary3_18_1_,
                guideobjec1_.staff_id as staff_id4_18_1_
            from
                Student_Object studentobj0_
            left outer join
                Guide_Object guideobjec1_ on studentobj0_.guide_id=guideobjec1_.id

            Amy Gill : 2014AL50456 : Ian Lamb
            John Smith : 2014JT50123 : Ian Lamb
            Rahul Singh : 2014RG50347 : David Crow

    */
    @Override
    public void displayStudentNameEnrollmentIdAndGuideName() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery("select student from StudentObject student left join fetch student.guide");
            List<StudentObject> students = query.getResultList();
            for (StudentObject student : students) {
                if (student.getGuide() != null) {
                    System.out.println(student.getName() + " : " + student.getEnrollmentId() + " : " + student.getGuide().getName());
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
