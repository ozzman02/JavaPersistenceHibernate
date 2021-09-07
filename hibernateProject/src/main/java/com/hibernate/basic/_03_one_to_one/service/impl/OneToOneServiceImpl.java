package com.hibernate.basic._03_one_to_one.service.impl;

import com.hibernate.basic._03_one_to_one.entity.Customer;
import com.hibernate.basic._03_one_to_one.entity.Passport;
import com.hibernate.basic._03_one_to_one.service.OneToOneService;
import com.hibernate.basic.util.DatabaseUtil;
import com.hibernate.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OneToOneServiceImpl implements OneToOneService {

    @Override
    public void clearCustomersAndPassports() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            DatabaseUtil.clear(session, Customer.class);
            DatabaseUtil.clear(session, Passport.class);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void saveCustomersWithPassports() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Passport passport = new Passport("925076473");
            Customer customer = new Customer("Nicole Scott", passport);
            Passport passport2 = new Passport("888876473");
            Customer customer2 = new Customer("Don Alfred", passport2);
            session.persist(customer);
            session.persist(customer2);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void updateCustomer() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Customer customer = session.get(Customer.class, 1L);
            customer.setName("Pablo Andrade");
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Customer customer = session.get(Customer.class, 1L);
            session.delete(customer);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
