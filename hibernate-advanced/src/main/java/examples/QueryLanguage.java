package examples;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Guide;

public class QueryLanguage {
	
	@SuppressWarnings("unchecked")
	public static void getGuides() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * JPQL query: select guide from Guide as guide
			 */
			Query query = em.createQuery("select guide from Guide as guide");
			
			List<Guide> guides = query.getResultList();
			
			for (Guide guide : guides) {
				System.out.println(guide);
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
	public static void getGuideNames() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createQuery("select guide.name from Guide guide");
			
			List<String> names = query.getResultList();
			
			for (String name : names) {
				System.out.println(name);
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
	public static void filterGuidesBySalary() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createQuery("select guide from Guide guide where guide.salary = 1000");
			
			List<Guide> guides = query.getResultList();
			
			for (Guide guide : guides) {
				System.out.println(guide);
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
	public static void guideReport() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createQuery("select guide.name, guide.salary from Guide guide");
			
			List<Object[]> resultList = query.getResultList();
			
			for (Object[] objects : resultList) {
				System.out.println("Object[] {objects[0]: " + objects[0] + ", objects[1]: " + objects[1] + "}");
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
	
	public static void dynamicQuery() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * 	Simulating dynamic parameter with variable name
			 * 
			 *	String name = "Ian Lamb";
			 *
			 * 	Query query = em.createQuery("select guide from Guide guide where "
			 	+ "guide.name = '" + name + "' ");
			 * 	
			 */
			
			 
			/*
			 * It's better to use named parameter
			 */
			
			Query query = em.createQuery("select guide from Guide guide where guide.name = :name");
			
			query.setParameter("name", "Ian Lamb");
			
			Guide guide = (Guide) query.getSingleResult();
			
			System.out.println(guide);
			
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
	public static void usingWildCard() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createQuery("select guide from Guide guide where guide.name like 'M%'");
			
			List<Guide> guides = query.getResultList();
			
			for (Guide guide : guides) {
				System.out.println(guide);
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
	public static void nativeSQL() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createNativeQuery("select * from guide", Guide.class);
			
			List<Guide> guides = query.getResultList();
			
			for (Guide guide : guides) {
				System.out.println(guide);
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
	public static void namedQuery() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			List<Guide> guides = em.createNamedQuery("findByGuide")
					.setParameter("name", "Mike Lawson")
					.getResultList();
			
			for (Guide guide : guides) {
				System.out.println(guide);
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
	
	public static void countFunction() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createQuery("select count(guide) from Guide guide");
			
			Long numOfGuides = (Long) query.getSingleResult();
			
			System.out.println("[numOfGuides:" + numOfGuides + "]");
			
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
	
	public static void maxFunction() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Query query = em.createQuery("select max(guide.salary) from Guide guide");
			
			Integer maximumSalary = (Integer) query.getSingleResult();
			
			System.out.println("[maximum salary:" + maximumSalary + "]");
			
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
