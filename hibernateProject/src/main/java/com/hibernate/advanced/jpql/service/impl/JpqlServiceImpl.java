package com.hibernate.advanced.jpql.service.impl;

import com.hibernate.advanced.jpql.entity.Guide3;
import com.hibernate.advanced.jpql.entity.Student3;
import com.hibernate.advanced.jpql.service.JpqlService;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

import static com.hibernate.advanced.constants.Constants.PERSISTENCE_UNIT_NAME;

public class JpqlServiceImpl implements JpqlService {

    private static final String LIST_GUIDES = "select guide from Guide3 as guide";

    private static final String GET_GUIDE_BY_SALARY
            = "select guide from Guide3 guide where guide.salary = 1000";

    private static final String LIST_GUIDES_REPORT
            = "select guide.name, guide.salary from Guide3 as guide";

    private static final String LIST_GUIDES_NATIVE_QUERY = "select * from guide3";

    private static final String FIND_GUIDE_BY_NAME = "findGuideByName";

    private static final String COUNT_GUIDES_QUERY = "select count(guide) from Guide3 guide";

    private static final String GET_MAX_SALARY_QUERY = "select max(guide.salary) from Guide3 guide";

    private static final String INNER_JOIN_QUERY
            = "select student from Student3 student join student.guide guide";

    private static final String LEFT_JOIN_QUERY
            = "select student from Student3 student left join student.guide guide";

    private static final String RIGHT_JOIN_QUERY
            = "select student from Student3 student right join student.guide guide";

    private static final String JOIN_FETCH_QUERY
            = "select guide from Guide3 guide join fetch guide.students student";

    private static final String STUDENTS_WITH_NO_GUIDE_QUERY
            = "select student.name, student.enrollmentId from Student3 student where student.guide is null";

    private static final String GUIDES_WITH_NO_STUDENTS_QUERY
            = "select g.name, g.staffId from Guide3 g where size(g.students) is 0";

    private static final String GUIDES_WITH_AT_LEAST_ONE_STUDENT_QUERY
            = "select g from Guide3 g join g.students s where s.name like 'A%'";

    @Override
    public void saveStudentsAndGuides() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Guide3 guide1 = new Guide3("2000MO10789", "Mike Lawson", 1000);
            Guide3 guide2 = new Guide3("2000IM10901", "Ian Lamb", 2000);
            Guide3 guide3 = new Guide3("2000IM10888", "Dean Ring", 2000);
            Student3 student1 = new Student3("2014JT50123", "John Smith", guide1);
            Student3 student2 = new Student3("2014AL50456", "Amy Gill", guide1);
            Student3 student3 = new Student3("2014AL50333", "Oscar Santamaria", guide2);
            Student3 student4 = new Student3("2014AL50222", "John Lopez");
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

    @Override
    public void listGuides() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(LIST_GUIDES);
            List<Guide3> guides = query.getResultList();
            for (Guide3 guide : guides) {
                System.out.println(guide);
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
    public void getGuideBySalary() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(GET_GUIDE_BY_SALARY);
            List<Guide3> guides = query.getResultList();
            for (Guide3 guide : guides) {
                System.out.println(guide);
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
    public void listGuidesReport() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(LIST_GUIDES_REPORT);
            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                System.out.println(Arrays.toString(objects));
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
    public void getGuideByName(String name) {
        String getGuideByNameQuery = "select guide from Guide3 guide where guide.name ='" + name + "'";
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(getGuideByNameQuery);
            Guide3 guide = (Guide3) query.getSingleResult();
            System.out.println(guide);
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
    public void getGuideByNameWithNamedParameter(String name) {
        String getGuideByNameQuery = "select guide from Guide3 guide where guide.name = :name";
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(getGuideByNameQuery);
            query.setParameter("name", name);
            Guide3 guide = (Guide3) query.getSingleResult();
            System.out.println(guide);
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
    public void getGuideByNameUsingLikePattern() {
        String getGuideByNameQuery = "select guide from Guide3 guide where guide.name like 'M%'";
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(getGuideByNameQuery);
            List<Guide3> guides = query.getResultList();
            for (Guide3 guide : guides) {
                System.out.println(guide);
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
    public void listGuidesWithNativeQuery() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createNativeQuery(LIST_GUIDES_NATIVE_QUERY, Guide3.class);
            List<Guide3> guides = query.getResultList();
            for (Guide3 guide : guides) {
                System.out.println(guide);
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
    public void findGuideByName(String name) {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            List<Guide3> guides = entityManager
                    .createNamedQuery(FIND_GUIDE_BY_NAME)
                    .setParameter("name", name)
                    .getResultList();
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

    @Override
    public void getNumberOfGuides() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            int numberOfGuides = entityManager.createQuery(LIST_GUIDES).getResultList().size();
            System.out.println("Number of guides " + numberOfGuides);
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
    public void countGuides() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Long numberOfGuides = (Long) entityManager.createQuery(COUNT_GUIDES_QUERY).getSingleResult();
            System.out.println("Number of guides " + numberOfGuides);
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
    public void getMaxSalary() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Integer maximumSalary = (Integer) entityManager.createQuery(GET_MAX_SALARY_QUERY).getSingleResult();
            System.out.println("Maximum Salary is " + maximumSalary);
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
    public void innerJoin() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(INNER_JOIN_QUERY);
            List<Student3> students = query.getResultList();
            students.forEach(System.out::println);
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
    public void leftJoin() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(LEFT_JOIN_QUERY);
            List<Student3> students = query.getResultList();
            students.forEach(System.out::println);
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
    public void rightJoin() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(RIGHT_JOIN_QUERY);
            List<Student3> students = query.getResultList();
            students.forEach(System.out::println);
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
    public void joinFetch() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(JOIN_FETCH_QUERY);
            List<Guide3> guides = query.getResultList();
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
    public void studentsWithNoGuideReport() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(STUDENTS_WITH_NO_GUIDE_QUERY);
            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                System.out.println(Arrays.toString(objects));
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
    public void guidesWithNoStudentsReport() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(GUIDES_WITH_NO_STUDENTS_QUERY);
            List<Object[]> resultList = query.getResultList();
            for (Object[] objects : resultList) {
                System.out.println(Arrays.toString(objects));
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
    public void guidesWithAtLeastOneStudent() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Query query = entityManager.createQuery(GUIDES_WITH_AT_LEAST_ONE_STUDENT_QUERY);
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

    @Override
    public void flushingWithDefault() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Student3 student = entityManager.find(Student3.class, 3L);
            student.setName("Oscar Venegas");
            String selectStatement = "select s.name from Student3 s where s.id = :id";
            /* Synchronizes/flushes persistence context before the query is executed
            *  meaning that an update statement is issued */
            Query query = entityManager.createQuery(selectStatement).setParameter("id", 3L);
            String name = (String) query.getSingleResult();
            entityTransaction.commit(); // no update issued
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
    public void flushingWithCommitMode() {
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        try {
            entityTransaction.begin();
            Student3 student = entityManager.find(Student3.class, 3L);
            student.setName("Oscar Santamaria");
            String selectStatement = "select s.name from Student3 s where s.id = :id";
            Query query = entityManager.createQuery(selectStatement).setParameter("id", 3L);
            entityManager.setFlushMode(FlushModeType.COMMIT);
            // A select statement is issued, no flushing takes place
            String name = (String) query.getSingleResult();
            // Here the update statement is issued
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
