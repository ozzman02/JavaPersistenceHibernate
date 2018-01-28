package examples;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entity.Guide;
import entity.Student;


public class OneToMany {
	
	public static void loadGuideAndStudents() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

        try {
            txn.begin();
            Guide guide1 = new Guide("2000MO10789", "Mike Lawson", 1000);
            Guide guide2 = new Guide("2000IM10901", "Ian Lamb", 2000);
            Student student1 = new Student("2014JT50123", "John Smith", guide1);
            Student student2 = new Student("2014AL50456", "Amy Gill", guide1);
            guide1.getStudents().add(student1);
            guide1.getStudents().add(student2);
            em.persist(guide1);
            em.persist(guide2);
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
	
	public static void updateStudent() {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

        try {
            txn.begin();
            Guide guide = (Guide) em.find(Guide.class, 2L);
            Student student = (Student) em.find(Student.class, 1L);
            Student student2 = (Student) em.find(Student.class, 2L);
            guide.addStudent(student);
            guide.addStudent(student2);
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

	@SuppressWarnings("unused")
	public static void findGuide() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			Guide guide = em.find(Guide.class, 2L);
			//Set<Student> students = guide.getStudents();
			//int numberOfStudents = students.size();
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
	
	@SuppressWarnings("unused")
	public static void findStudent() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			Student student = em.find(Student.class, 2L);
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
	
	public static void compareStudents() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/* Student student1 = new Student("2013HG10543", "Harry Page");
			Student student2 = new Student("2013HG10543", "Harry Page");
			System.out.println("Equals: " + student1.equals(student2));
			System.out.println("Hash code: " + String.valueOf(student1.hashCode() == student2.hashCode()));
			Set<Student> students = new HashSet<Student>();
			students.add(student1);
			System.out.println(students.contains(student2));*/
			
			Student student = em.find(Student.class, 2L); // Eager
			
			Guide guide = em.find(Guide.class, 2L); // Lazy
			
			Set<Student> students = guide.getStudents();
			
			System.out.println(students.contains(student)); // collection of students is initialized 
			
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
