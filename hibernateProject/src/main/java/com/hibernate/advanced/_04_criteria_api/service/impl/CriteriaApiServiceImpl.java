package com.hibernate.advanced._04_criteria_api.service.impl;

import com.hibernate.advanced._04_criteria_api.service.CriteriaApiService;
import com.hibernate.advanced._03_jpql.entity.Guide3;
import com.hibernate.advanced._03_jpql.entity.Guide3_;
import com.hibernate.advanced._03_jpql.entity.Student3;
import com.hibernate.advanced._03_jpql.entity.Student3_;

import javax.persistence.*;
import javax.persistence.criteria.*;

import java.util.Arrays;
import java.util.List;

import static com.hibernate.advanced.constants.Constants.*;

public class CriteriaApiServiceImpl implements CriteriaApiService {

    /* select guide from Guide3 guide */
    @Override
    public void getGuide() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            criteriaQuery.select(root);
            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);
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

    /* select guide.name from Guide3 guide */
    @Override
    public void getGuideNames() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            Path<String> name = root.get(Guide3_.name);
            criteriaQuery.select(name);
            TypedQuery<String> query = entityManager.createQuery(criteriaQuery);
            List<String> names = query.getResultList();
            names.forEach(System.out::println);
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

    /* select guide.name, guide.salary from Guide3 guide */
    @Override
    public void getMultipleGuideFields() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            Path<String> name = root.get(Guide3_.name);
            Path<Integer> salary = root.get(Guide3_.salary);
            /* criteriaQuery.multiselect(name, salary) is also valid, we are still using the select method */
            criteriaQuery.select(criteriaBuilder.array(name, salary));
            TypedQuery<Object[]> query = entityManager.createQuery(criteriaQuery);
            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) System.out.println(Arrays.toString(objects));
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

    /* select guide from Guide3 guide where guide.salary = 1000 */
    @Override
    public void filteringResults() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            Path<Integer> salary = root.get(Guide3_.salary);
            /*
                Other criteriaBuilder methods

                    criteriaBuilder.gt(salary, 1000);
                    criteriaBuilder.between(salary, 0, 5000);
                    criteriaBuilder.like(staffId, "2000%");
            */
            criteriaQuery.where(criteriaBuilder.equal(salary, 1000));
            criteriaQuery.select(root);
            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery);
            List<Guide3> resultList = query.getResultList();
            for (Guide3 guide : resultList) System.out.println(guide);
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

    /* Dynamic Query: select guide from Guide3 guide where guide.name = :name */
    @Override
    public void findGuideByName(String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get(Guide3_.name), criteriaBuilder.parameter(String.class, "name")));
            criteriaQuery.select(root);
            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery).setParameter("name", name);
            List<Guide3> resultList = query.getResultList();
            resultList.forEach(System.out::println);
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

    /* Wildcards: select guide from Guide3 guide where guide.staffId like '2000%' */
    @Override
    public void findGuideByStaffId() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            Path<String> staffId = root.get(Guide3_.staffId);
            criteriaQuery.where(criteriaBuilder.like(staffId, "2000%"));
            criteriaQuery.select(root);
            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery);
            List<Guide3> resultList = query.getResultList();
            for (Guide3 guide : resultList) System.out.println(guide);
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

    /* Aggregate function: select count(guide) from Guide3 guide */
    @Override
    public void countGuides() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            criteriaQuery.select(criteriaBuilder.count(root));
            TypedQuery<Long> query = entityManager.createQuery(criteriaQuery);
            Long numberOfGuides = query.getSingleResult();
            System.out.printf("Number of Guides: %d%n", numberOfGuides);
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

    /* Aggregate function: select max(guide.salary) from Guide3 guide */
    @Override
    public void getGuideMaxSalary() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);
            Path<Integer> salary = root.get(Guide3_.salary);
            criteriaQuery.select(criteriaBuilder.max(salary));
            TypedQuery<Integer> query = entityManager.createQuery(criteriaQuery);
            Integer maxSalary = query.getSingleResult();
            System.out.printf("Max Salary: %d%n", maxSalary);
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

    /* Call named query from orm.xml */
    @Override
    public void getGuideByName(String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            TypedQuery<Guide3> query = entityManager.createNamedQuery(FIND_GUIDE_BY_NAME, Guide3.class).setParameter("name", name);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);
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

    /* Named Queries using @NamedQuery */
    @Override
    public void printGuideByName(String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            TypedQuery<Guide3> query = entityManager.createNamedQuery("Guide.findByName", Guide3.class).setParameter("name", name);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);
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

    /* Named Queries using @NamedQuery */
    @Override
    public void findAllGuides() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            TypedQuery<Guide3> query = entityManager.createNamedQuery("Guide.findAll", Guide3.class);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);
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

    /* Named Queries using @NamedQuery */
    @Override
    public void findGuideById(Long id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            TypedQuery<Guide3> query = entityManager.createNamedQuery("Guide.findById", Guide3.class).setParameter("id", id);
            Guide3 guide = query.getSingleResult();
            System.out.println(guide);
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

    /* Native Query: select * from guide */
    @Override
    public void getAllGuidesNativeQuery() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createNativeQuery(LIST_GUIDES_NATIVE_QUERY, Guide3.class);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);
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

    /* select student from Student3 student join student.guide guide */
    /*
        select
            student3x0_.id as id1_26_,
            student3x0_.enrollment_id as enrollme2_26_,
            student3x0_.guide_id as guide_id4_26_,
            student3x0_.name as name3_26_
        from
            Student3 student3x0_
        inner join
            Guide3 guide3x1_
                on student3x0_.guide_id=guide3x1_.id

        select
            guide3x0_.id as id1_13_0_,
            guide3x0_.name as name2_13_0_,
            guide3x0_.salary as salary3_13_0_,
            guide3x0_.staff_id as staff_id4_13_0_
        from
            Guide3 guide3x0_
        where
            guide3x0_.id=?

        23:08:07,065 TRACE BasicBinder:64 - binding parameter [1] as [BIGINT] - [1]

        select
            guide3x0_.id as id1_13_0_,
            guide3x0_.name as name2_13_0_,
            guide3x0_.salary as salary3_13_0_,
            guide3x0_.staff_id as staff_id4_13_0_
        from
            Guide3 guide3x0_
        where
            guide3x0_.id=?

        23:08:07,076 TRACE BasicBinder:64 - binding parameter [1] as [BIGINT] - [2]

        Student3[id=1, enrollmentId='2014JT50123', name='John Smith', guide='Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]']
        Student3[id=2, enrollmentId='2014AL50456', name='Amy Gill', guide='Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]']
        Student3[id=3, enrollmentId='2014AL50333', name='Oscar Santamaria', guide='Guide3[id=2, staffId='2000IM10901', name='Ian Lamb', salary=2000]']
    */
    @Override
    public void innerJoin() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Student3> criteriaQuery = criteriaBuilder.createQuery(Student3.class);
            Root<Student3> root = criteriaQuery.from(Student3.class);

            /* Student.guide is a @ManyToOne */
            Join<Student3, Guide3> guide = root.join(Student3_.guide);
            criteriaQuery.select(root);

            TypedQuery<Student3> query = entityManager.createQuery(criteriaQuery);
            List<Student3> students = query.getResultList();
            students.forEach(System.out::println);

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

    /* select guide from Guide3 guide join guide.students student */
    /*
        select
            guide3x0_.id as id1_13_,
            guide3x0_.name as name2_13_,
            guide3x0_.salary as salary3_13_,
            guide3x0_.staff_id as staff_id4_13_
        from
            Guide3 guide3x0_
        inner join
            Student3 students1_
                on guide3x0_.id=students1_.guide_id

        Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]
        Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]
        Guide3[id=2, staffId='2000IM10901', name='Ian Lamb', salary=2000]
    */
    @Override
    public void innerJoin2() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);

            /* Guide.students is a @OneToMany */
            Join<Guide3, Student3> students = root.join(Guide3_.students);
            criteriaQuery.select(root);

            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);

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

    /* select student from Student3 student left join student.guide guide */
    /*
        select
            student3x0_.id as id1_26_,
            student3x0_.enrollment_id as enrollme2_26_,
            student3x0_.guide_id as guide_id4_26_,
            student3x0_.name as name3_26_
        from
            Student3 student3x0_
        left outer join
            Guide3 guide3x1_
                on student3x0_.guide_id=guide3x1_.id

        select
            guide3x0_.id as id1_13_0_,
            guide3x0_.name as name2_13_0_,
            guide3x0_.salary as salary3_13_0_,
            guide3x0_.staff_id as staff_id4_13_0_
        from
            Guide3 guide3x0_
        where
            guide3x0_.id=?

        23:13:10,940 TRACE BasicBinder:64 - binding parameter [1] as [BIGINT] - [1]

        select
            guide3x0_.id as id1_13_0_,
            guide3x0_.name as name2_13_0_,
            guide3x0_.salary as salary3_13_0_,
            guide3x0_.staff_id as staff_id4_13_0_
        from
            Guide3 guide3x0_
        where
            guide3x0_.id=?

        23:13:10,951 TRACE BasicBinder:64 - binding parameter [1] as [BIGINT] - [2]


        Student3[id=1, enrollmentId='2014JT50123', name='John Smith', guide='Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]']
        Student3[id=2, enrollmentId='2014AL50456', name='Amy Gill', guide='Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]']
        Student3[id=3, enrollmentId='2014AL50333', name='Oscar Santamaria', guide='Guide3[id=2, staffId='2000IM10901', name='Ian Lamb', salary=2000]']
        Student3[id=4, enrollmentId='2014AL50222', name='John Lopez', guide='null']
    */
    @Override
    public void leftJoin() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Student3> criteriaQuery = criteriaBuilder.createQuery(Student3.class);
            Root<Student3> root = criteriaQuery.from(Student3.class);

            /* Student.guide is a @ManyToOne */
            Join<Student3, Guide3> guide = root.join(Student3_.guide, JoinType.LEFT);
            criteriaQuery.select(root);

            TypedQuery<Student3> query = entityManager.createQuery(criteriaQuery);
            List<Student3> students = query.getResultList();
            students.forEach(System.out::println);

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

    /* select guide from Guide3 guide join fetch guide.students student */
    /*
        select
            guide3x0_.id as id1_13_0_,
            students1_.id as id1_26_1_,
            guide3x0_.name as name2_13_0_,
            guide3x0_.salary as salary3_13_0_,
            guide3x0_.staff_id as staff_id4_13_0_,
            students1_.enrollment_id as enrollme2_26_1_,
            students1_.guide_id as guide_id4_26_1_,
            students1_.name as name3_26_1_,
            students1_.guide_id as guide_id4_26_0__,
            students1_.id as id1_26_0__
        from
            Guide3 guide3x0_
        inner join
            Student3 students1_
                on guide3x0_.id=students1_.guide_id

        Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]
        Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]
        Guide3[id=2, staffId='2000IM10901', name='Ian Lamb', salary=2000]
    */
    @Override
    public void innerJoinFetch() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);

            /* Guide.students is a @OneToMany */
            Fetch<Guide3, Student3> students = root.fetch(Guide3_.students);
            criteriaQuery.select(root);

            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);

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

    /* select guide from Guide3 guide left join fetch guide.students student */
    /*
        select
            guide3x0_.id as id1_13_0_,
            students1_.id as id1_26_1_,
            guide3x0_.name as name2_13_0_,
            guide3x0_.salary as salary3_13_0_,
            guide3x0_.staff_id as staff_id4_13_0_,
            students1_.enrollment_id as enrollme2_26_1_,
            students1_.guide_id as guide_id4_26_1_,
            students1_.name as name3_26_1_,
            students1_.guide_id as guide_id4_26_0__,
            students1_.id as id1_26_0__
        from
            Guide3 guide3x0_
        left outer join
            Student3 students1_
                on guide3x0_.id=students1_.guide_id

        Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]
        Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]
        Guide3[id=2, staffId='2000IM10901', name='Ian Lamb', salary=2000]
        Guide3[id=3, staffId='2000IM10888', name='Dean Ring', salary=2000]
    */
    @Override
    public void leftJoinFetch() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);

            /* Guide.students is a @OneToMany */
            Fetch<Guide3, Student3> students = root.fetch(Guide3_.students, JoinType.LEFT);
            criteriaQuery.select(root);

            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);

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

    /* select distinct(guide) from Guide3 guide left join fetch guide.students student */
    /*
        select
            distinct guide3x0_.id as id1_13_0_,
            students1_.id as id1_26_1_,
            guide3x0_.name as name2_13_0_,
            guide3x0_.salary as salary3_13_0_,
            guide3x0_.staff_id as staff_id4_13_0_,
            students1_.enrollment_id as enrollme2_26_1_,
            students1_.guide_id as guide_id4_26_1_,
            students1_.name as name3_26_1_,
            students1_.guide_id as guide_id4_26_0__,
            students1_.id as id1_26_0__
        from
            Guide3 guide3x0_
        left outer join
            Student3 students1_
                on guide3x0_.id=students1_.guide_id

        Guide3[id=1, staffId='2000MO10789', name='Mike Lawson', salary=1000]
        Guide3[id=2, staffId='2000IM10901', name='Ian Lamb', salary=2000]
        Guide3[id=3, staffId='2000IM10888', name='Dean Ring', salary=2000]
    */
    @Override
    public void fetchingDistinctResults() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Guide3> criteriaQuery = criteriaBuilder.createQuery(Guide3.class).distinct(true);
            Root<Guide3> root = criteriaQuery.from(Guide3.class);

            /* Guide.students is a @OneToMany */
            Fetch<Guide3, Student3> students = root.fetch(Guide3_.students, JoinType.LEFT);
            criteriaQuery.select(root);

            TypedQuery<Guide3> query = entityManager.createQuery(criteriaQuery);
            List<Guide3> guides = query.getResultList();
            guides.forEach(System.out::println);

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


}
