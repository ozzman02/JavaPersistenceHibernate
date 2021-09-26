package com.hibernate.advanced._04_criteria_api.service.impl;

import com.hibernate.advanced._04_criteria_api.entity.GuideForCriteriaApi;
import com.hibernate.advanced._04_criteria_api.entity.GuideForCriteriaApi_;
import com.hibernate.advanced._04_criteria_api.entity.StudentForCriteriaApi;
import com.hibernate.advanced._04_criteria_api.entity.StudentForCriteriaApi_;
import com.hibernate.advanced._04_criteria_api.service.CriteriaApiService;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

import static com.hibernate.advanced._13_constants.Constants.*;

public class CriteriaApiServiceImpl implements CriteriaApiService {

    @Override
    public void saveStudentsAndGuides() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            GuideForCriteriaApi guide1 = new GuideForCriteriaApi("2000MO10789", "Mike Lawson", 1000);
            GuideForCriteriaApi guide2 = new GuideForCriteriaApi("2000IM10901", "Ian Lamb", 2000);
            GuideForCriteriaApi guide3 = new GuideForCriteriaApi("2000IM10888", "Dean Ring", 2000);
            StudentForCriteriaApi student1 = new StudentForCriteriaApi("2014JT50123", "John Smith", guide1);
            StudentForCriteriaApi student2 = new StudentForCriteriaApi("2014AL50456", "Amy Gill", guide1);
            StudentForCriteriaApi student3 = new StudentForCriteriaApi("2014AL50333", "Oscar Santamaria", guide2);
            StudentForCriteriaApi student4 = new StudentForCriteriaApi("2014AL50222", "John Lopez");
            guide1.addStudent(student1);
            guide1.addStudent(student2);
            guide2.addStudent(student3);
            entityManager.persist(guide1);
            entityManager.persist(guide2);
            entityManager.persist(guide3);
            entityManager.persist(student4);
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

    /* select guide from GuideForCriteriaApi guide */
    @Override
    public void getGuide() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
            criteriaQuery.select(root);
            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<GuideForCriteriaApi> guides = query.getResultList();
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

    /* select guide.name from GuideForCriteriaApi guide */
    @Override
    public void getGuideNames() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = criteriaBuilder.createQuery(String.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
            Path<String> name = root.get(GuideForCriteriaApi_.name);
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

    /* select guide.name, guide.salary from GuideForCriteriaApi guide */
    @Override
    public void getMultipleGuideFields() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery(Object[].class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
            Path<String> name = root.get(GuideForCriteriaApi_.name);
            Path<Integer> salary = root.get(GuideForCriteriaApi_.salary);
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

    /* select guide from GuideForCriteriaApi guide where guide.salary = 1000 */
    @Override
    public void filteringResults() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
            Path<Integer> salary = root.get(GuideForCriteriaApi_.salary);
            /*
                Other criteriaBuilder methods

                    criteriaBuilder.gt(salary, 1000);
                    criteriaBuilder.between(salary, 0, 5000);
                    criteriaBuilder.like(staffId, "2000%");
            */
            criteriaQuery.where(criteriaBuilder.equal(salary, 1000));
            criteriaQuery.select(root);
            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<GuideForCriteriaApi> resultList = query.getResultList();
            for (GuideForCriteriaApi guide : resultList) System.out.println(guide);
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

    /* Dynamic Query: select guide from GuideForCriteriaApi guide where guide.name = :name */
    @Override
    public void findGuideByName(String name) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
            criteriaQuery.where(criteriaBuilder.equal(root.get(GuideForCriteriaApi_.name), criteriaBuilder.parameter(String.class, "name")));
            criteriaQuery.select(root);
            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery).setParameter("name", name);
            List<GuideForCriteriaApi> resultList = query.getResultList();
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

    /* Wildcards: select guide from GuideForCriteriaApi guide where guide.staffId like '2000%' */
    @Override
    public void findGuideByStaffId() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
            Path<String> staffId = root.get(GuideForCriteriaApi_.staffId);
            criteriaQuery.where(criteriaBuilder.like(staffId, "2000%"));
            criteriaQuery.select(root);
            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<GuideForCriteriaApi> resultList = query.getResultList();
            for (GuideForCriteriaApi guide : resultList) System.out.println(guide);
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

    /* Aggregate function: select count(guide) from GuideForCriteriaApi guide */
    @Override
    public void countGuides() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
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

    /* Aggregate function: select max(guide.salary) from GuideForCriteriaApi guide */
    @Override
    public void getGuideMaxSalary() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);
            Path<Integer> salary = root.get(GuideForCriteriaApi_.salary);
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
            TypedQuery<GuideForCriteriaApi> query = entityManager.createNamedQuery(GET_GUIDE_BY_NAME, GuideForCriteriaApi.class).setParameter("name", name);
            List<GuideForCriteriaApi> guides = query.getResultList();
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
            TypedQuery<GuideForCriteriaApi> query = entityManager.createNamedQuery("Guide.findByName", GuideForCriteriaApi.class).setParameter("name", name);
            List<GuideForCriteriaApi> guides = query.getResultList();
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
            TypedQuery<GuideForCriteriaApi> query = entityManager.createNamedQuery("Guide.findAll", GuideForCriteriaApi.class);
            List<GuideForCriteriaApi> guides = query.getResultList();
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
            TypedQuery<GuideForCriteriaApi> query = entityManager.createNamedQuery("Guide.findById", GuideForCriteriaApi.class).setParameter("id", id);
            GuideForCriteriaApi guide = query.getSingleResult();
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
            Query query = entityManager.createNativeQuery(LIST_GUIDES_NATIVE_QUERY2, GuideForCriteriaApi.class);
            List<GuideForCriteriaApi> guides = query.getResultList();
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

    /* select student from StudentForCriteriaApi student join student.guide guide */
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
            CriteriaQuery<StudentForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(StudentForCriteriaApi.class);
            Root<StudentForCriteriaApi> root = criteriaQuery.from(StudentForCriteriaApi.class);

            /* Student.guide is a @ManyToOne */
            Join<StudentForCriteriaApi, GuideForCriteriaApi> guide = root.join(StudentForCriteriaApi_.guide);
            criteriaQuery.select(root);

            TypedQuery<StudentForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<StudentForCriteriaApi> students = query.getResultList();
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
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);

            /* Guide.students is a @OneToMany */
            Join<GuideForCriteriaApi, StudentForCriteriaApi> students = root.join(GuideForCriteriaApi_.students);
            criteriaQuery.select(root);

            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<GuideForCriteriaApi> guides = query.getResultList();
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
            CriteriaQuery<StudentForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(StudentForCriteriaApi.class);
            Root<StudentForCriteriaApi> root = criteriaQuery.from(StudentForCriteriaApi.class);

            /* Student.guide is a @ManyToOne */
            Join<StudentForCriteriaApi, GuideForCriteriaApi> guide = root.join(StudentForCriteriaApi_.guide, JoinType.LEFT);
            criteriaQuery.select(root);

            TypedQuery<StudentForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<StudentForCriteriaApi> students = query.getResultList();
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
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);

            /* Guide.students is a @OneToMany */
            Fetch<GuideForCriteriaApi, StudentForCriteriaApi> students = root.fetch(GuideForCriteriaApi_.students);
            criteriaQuery.select(root);

            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<GuideForCriteriaApi> guides = query.getResultList();
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
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);

            /* Guide.students is a @OneToMany */
            Fetch<GuideForCriteriaApi, StudentForCriteriaApi> students = root.fetch(GuideForCriteriaApi_.students, JoinType.LEFT);
            criteriaQuery.select(root);

            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<GuideForCriteriaApi> guides = query.getResultList();
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
            CriteriaQuery<GuideForCriteriaApi> criteriaQuery = criteriaBuilder.createQuery(GuideForCriteriaApi.class).distinct(true);
            Root<GuideForCriteriaApi> root = criteriaQuery.from(GuideForCriteriaApi.class);

            /* Guide.students is a @OneToMany */
            Fetch<GuideForCriteriaApi, StudentForCriteriaApi> students = root.fetch(GuideForCriteriaApi_.students, JoinType.LEFT);
            criteriaQuery.select(root);

            TypedQuery<GuideForCriteriaApi> query = entityManager.createQuery(criteriaQuery);
            List<GuideForCriteriaApi> guides = query.getResultList();
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
