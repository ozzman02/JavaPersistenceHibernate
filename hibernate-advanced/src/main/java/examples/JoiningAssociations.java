package examples;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Guide;
import entity.Student;

public class JoiningAssociations {
	
	@SuppressWarnings("unchecked")
	public static void innerJoinStudents() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * List all the students that have a guide. The guide data is eagerly fetched
			 */
			Query query = em.createQuery("select student from Student student join student.guide guide");
			
			List<Student> students = query.getResultList();
			
			System.out.println();
			
			for (Student student : students) {
				System.out.println(student);
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
	
	@SuppressWarnings("unchecked")
	public static void leftJoinStudents() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * List all the students. Student table is the left table. It will bring all the students
			 */
			Query query = em.createQuery("select student from Student student left join student.guide guide");
			
			List<Student> students = query.getResultList();
			
			System.out.println();
			
			for (Student student : students) {
				System.out.println(student);
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
	
	@SuppressWarnings("unchecked")
	public static void rightJoinStudents() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createQuery("select student from Student student right join student.guide guide");
			
			List<Student> students = query.getResultList();
			
			System.out.println();
			
			for (Student student : students) {
				System.out.println(student);
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
	
	@SuppressWarnings({ "unchecked", "unused" })
	public static void innerJoinGuides() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Students attribute is annotated as a @OneToMany relationship with a default 
			 * fetching strategy (Lazy). The following query will get only the guides
			 * 
			 * "select guide from Guide guide join guide.students student"
			 * 
			 * In order to eagerly fetch you dont need to change the fetch type like this
			 * fetch = FetchType.EAGER, you can do it in the query with the fetch keyword.
			 * The following query will get the guides and the associated students.
			 * 
			 * "select guide from Guide guide join fetch guide.students student"
			 * 
			 */
			
			Query query = em.createQuery("select guide from Guide guide join fetch guide.students student");
			
			List<Guide> guides = query.getResultList();
			
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
