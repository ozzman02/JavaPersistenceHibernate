package examples;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import entity.Animal;
import entity.Animal2;
import entity.Animal3;
import entity.Cat;
import entity.Cat2;
import entity.Cat3;
import entity.Dog;
import entity.Dog2;
import entity.Dog3;

public class InheritanceMapping {
	
	public static void singleTableStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Cat cat = new Cat();
			cat.setName("Lucy");
			
			Dog dog = new Dog();
			dog.setName("Oliver");
			
			em.persist(cat);
			em.persist(dog);
			
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
	public static void getAnimalsSingleTableStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select animal from Animal animal");
			
			List<Animal> animals = query.getResultList();
			
			System.out.println();
			
			for (Animal animal : animals) {
				System.out.println(animal);
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
	public static void getDogsSingleTableStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select dog from Dog dog");
			
			List<Dog> dogs = query.getResultList();
			
			System.out.println();
			
			for (Dog dog : dogs) {
				System.out.println(dog);
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
	
	public static void joinedStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Cat2 cat = new Cat2();
			cat.setName("Lucy");
			
			Dog2 dog = new Dog2();
			dog.setName("Oliver");
			
			em.persist(cat);
			em.persist(dog);
			
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
	public static void getAnimalsJoinedStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select animal2 from Animal2 animal2");
			
			List<Animal2> animals = query.getResultList();
			
			System.out.println();
			
			for (Animal2 animal2 : animals) {
				System.out.println(animal2);
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
	public static void getDogsJoinedStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select dog2 from Dog2 dog2");
			
			List<Dog2> dogs = query.getResultList();
			
			System.out.println();
			
			for (Dog2 dog2 : dogs) {
				System.out.println(dog2);
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
	public static void getCatsJoinedStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select cat2 from Cat2 cat2");
			
			List<Cat2> cats = query.getResultList();
			
			System.out.println();
			
			for (Cat2 cat2 : cats) {
				System.out.println(cat2);
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
	
	public static void tablePerClassStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			Cat3 cat = new Cat3();
			cat.setName("Lucy");
			
			Dog3 dog = new Dog3();
			dog.setName("Oliver");
			
			em.persist(cat);
			em.persist(dog);
			
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
	public static void getAnimalsTablePerClassStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select animal3 from Animal3 animal3");
			
			List<Animal3> animals = query.getResultList();
			
			System.out.println();
			
			for (Animal3 animal : animals) {
				System.out.println(animal);
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
	public static void getDogsTablePerClassStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select dog3 from Dog3 dog3");
			
			List<Dog3> dogs = query.getResultList();
			
			System.out.println();
			
			for (Dog3 dog : dogs) {
				System.out.println(dog);
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
	public static void getCatsTablePerClassStrategy() {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hibernate-jpa");
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		
		try {
			txn.begin();
			
			/*
			 * Polymorphic Query
			 */
			Query query = em.createQuery("select cat3 from Cat3 cat3");
			
			List<Cat3> cats = query.getResultList();
			
			System.out.println();
			
			for (Cat3 cat : cats) {
				System.out.println(cat);
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
