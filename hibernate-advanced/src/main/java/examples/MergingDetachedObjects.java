package examples;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import entity.Guide;
import entity.Student;

public class MergingDetachedObjects {

	public static void mergeDetachedObj() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em1 = emf.createEntityManager();

		em1.getTransaction().begin();

		Guide guide = em1.find(Guide.class, 2L);
		Set<Student> students = guide.getStudents();
		int numOfStudents = students.size(); // initialize the proxy

		Student student = null;
		for (Student nextStudent : students) {
			if (nextStudent.getId() == 1L) {
				student = nextStudent;
			}
		}

		em1.getTransaction().commit();
		em1.close();

		guide.setSalary(2500);
		student.setName("Amy Jade Gill");

		EntityManager em2 = emf.createEntityManager();
		em2.getTransaction().begin();

		/*
		 * At this point only the salary is modified. If the name of the student needs
		 * to be copied we need to enable CascadeType.Merge in the OneToMany
		 * relationship in the Guide entity
		 */
		Guide mergedGuide = em2.merge(guide);

		em2.getTransaction().commit();
		em2.close();
	}

	/*
	 * Using just one entity manager merging is not needed
	 */
	public static void mergeDetachedObj2() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em1 = emf.createEntityManager();

		em1.getTransaction().begin();

		Guide guide = em1.find(Guide.class, 2L);
		Set<Student> students = guide.getStudents();
		int numOfStudents = students.size(); // initialize the proxy

		Student student = null;
		for (Student nextStudent : students) {
			if (nextStudent.getId() == 1L) {
				student = nextStudent;
			}
		}

		em1.getTransaction().commit();
		
		guide.setSalary(2500);
		student.setName("Amy Jade Gill");

		em1.getTransaction().begin();
		
		// merging not needed since the objs were never detached
		// CascadeType.Merge not needed
		
		em1.getTransaction().commit();
		em1.close();
		
	}

}
