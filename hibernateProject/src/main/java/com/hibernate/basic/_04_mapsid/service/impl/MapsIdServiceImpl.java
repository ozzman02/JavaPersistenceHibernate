package com.hibernate.basic._04_mapsid.service.impl;

import com.hibernate.basic._04_mapsid.entity.Customer2;
import com.hibernate.basic._04_mapsid.entity.Passport2;
import com.hibernate.basic._04_mapsid.service.MapsIdService;
import com.hibernate.basic._10_util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MapsIdServiceImpl implements MapsIdService {

    @Override
    public void saveCustomerWithPassport() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Passport2 passport = new Passport2("925076473");
            Customer2 customer = new Customer2("Nicole Scott", passport);
            session.persist(customer);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void getCustomer() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Passport2 passport = session.get(Passport2.class, 1L);
            Customer2 customer =  session.get(Customer2.class, passport.getId());
            transaction.commit();
            System.out.println(customer);
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
