package com.hibernate.basic._01_component_mapping.service.impl;

import com.hibernate.basic._01_component_mapping.entity.Address;
import com.hibernate.basic._01_component_mapping.entity.Person;
import com.hibernate.basic._01_component_mapping.service.ComponentMappingService;
import com.hibernate.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ComponentMappingServiceImpl implements ComponentMappingService {

    @Override
    public void savePerson() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Address homeAddress = new Address("200 E Main St", "Seattle", "85123");
            Address billingAddress = new Address("2751  Sigley Road", "Charlotte", "28273");
            Person person = new Person("Ruby", homeAddress, billingAddress);
            session.save(person);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
