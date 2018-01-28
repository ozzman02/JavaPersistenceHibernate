package examples;

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
	
}
