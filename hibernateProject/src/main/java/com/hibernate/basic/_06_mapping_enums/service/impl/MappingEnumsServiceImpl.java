package com.hibernate.basic._06_mapping_enums.service.impl;

import com.hibernate.basic._06_mapping_enums.entity.Employee;
import com.hibernate.basic._06_mapping_enums.entity.EmployeeStatus;
import com.hibernate.basic._06_mapping_enums.service.MappingEnumsService;
import com.hibernate.basic._10_util.HibernateUtil;
import org.hibernate.Session;

public class MappingEnumsServiceImpl implements MappingEnumsService {

    @Override
    public void saveEmployee() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Employee employee1 = new Employee("John Stockham", "2014JA11001", EmployeeStatus.FULL_TIME);
            Employee employee2 = new Employee("Ammie Corrio", "2014AI21543", EmployeeStatus.PART_TIME);
            Employee employee3 = new Employee("Ernie Stenseth", "2014ET25100", EmployeeStatus.CONTRACT);
            session.persist(employee1);
            session.persist(employee2);
            session.persist(employee3);
            session.getTransaction().commit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
