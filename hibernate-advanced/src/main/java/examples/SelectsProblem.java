package examples;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Guide;
import entity.Student;

/*
 * N + 1 Selects Problem Examples
 */
public class SelectsProblem {
	
	public static void loadGuideAndStudents() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

        try {
            txn.begin();
            Guide guide = new Guide("2000DO10777", "David Crow", 3000);
            Student student = new Student("2014RG50347", "Rahul Singh");
            guide.addStudent(student);
            em.persist(guide);
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
	
	/*
	 * 	How to solve:
	 * 
	 *	1. Change the fetching strategy of your single point associations (@ManyToOne and @OneToOne) from Eager to Lazy.
	 *	2. Write the query based on the requirements (e.g using left fetch join to load the child objects eagerly)
	 *	3. When number of parents becomes higher to change the fetching strategy to eager in order to have 
	 *		small n + 1 statements. 
	 *	4. The previous recommendation still not good enough for performance, consider Batch Fetching implementation. 
	 * 	
	 */
	
	@SuppressWarnings("unchecked")
	public static void getAllStudents() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

        try {
            txn.begin();
            
            Query query = em.createQuery("select student from Student student left join fetch student.guide");
            
            List<Student> students = query.getResultList();
            
            System.out.println();
            
            for (Student student : students) {
            	if (student.getGuide() != null) {
            		System.out.println(student.getName() + ":" + student.getEnrollmentId() + ":" + 
            				student.getGuide().getName());
            	}
			}
            
            txn.commit();
        } catch (Exception e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
	
}
