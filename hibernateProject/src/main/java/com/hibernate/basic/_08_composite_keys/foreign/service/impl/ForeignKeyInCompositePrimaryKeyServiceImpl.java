package com.hibernate.basic._08_composite_keys.foreign.service.impl;

import com.hibernate.basic._08_composite_keys.foreign.entity.Department;
import com.hibernate.basic._08_composite_keys.foreign.entity.User;
import com.hibernate.basic._08_composite_keys.foreign.entity.UserId;
import com.hibernate.basic._08_composite_keys.foreign.service.ForeignKeyInCompositePrimaryKeyService;
import com.hibernate.basic._10_util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ForeignKeyInCompositePrimaryKeyServiceImpl implements ForeignKeyInCompositePrimaryKeyService {

    @Override
    public void saveUser() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Department department = new Department("Psychology");
            session.persist(department);
            // setting department_id_cpk_col2
            UserId userId = new UserId("johndoe", department.getId());
            // setting department_id_fk
            User user = new User(userId, "johndoe@somewhere.com");
            user.setDepartment(department);
            // while saving the user object to database the departmentId (department.getId()) will be ignored
            // Hibernate will not create the department_id_cpk_col2 in the users table.
            session.persist(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void getUser() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            UserId userId = new UserId("johndoe", 1L);
            User user = session.get(User.class, userId);
            System.out.println(user);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
