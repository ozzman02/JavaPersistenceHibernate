package com.hibernate.basic.onetomany.service.impl;

import com.hibernate.basic.onetomany.entity.Guide;
import com.hibernate.basic.onetomany.entity.Student;
import com.hibernate.basic.onetomany.service.OneToManyService;
import com.hibernate.basic.util.DatabaseUtil;
import com.hibernate.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class OneToManyServiceImpl implements OneToManyService {

    @Override
    public void clearGuidesAndStudents() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            DatabaseUtil.clear(session, Guide.class);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void saveGuideAndStudents() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Guide guide1 = new Guide("2000MO10789", "Mike Lawson", 1000);
            Guide guide2 = new Guide("2000IM10901", "Ian Lamb", 2000);
            Guide guide3 = new Guide("2000IM10888", "Dean Ring", 2000);
            Student student1 = new Student("2014JT50123", "John Smith", guide1);
            Student student2 = new Student("2014AL50456", "Amy Gill", guide1);
            Student student3 = new Student("2014AL50333", "Oscar Santamaria", guide2);
            Student student4 = new Student("2014AL50222", "John Lopez", guide3);
            guide1.getStudents().add(student1);
            guide1.getStudents().add(student2);
            guide2.getStudents().add(student3);
            guide3.getStudents().add(student4);
            session.persist(guide1);
            session.persist(guide2);
            session.persist(guide3);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void updateStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Guide guide = session.get(Guide.class, 2L);
            Student student = session.get(Student.class, 1L);
            guide.addStudent(student);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteStudent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Student student = session.get(Student.class, 3L);
            session.delete(student);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteGuide() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Guide guide = session.get(Guide.class, 3L);
            session.delete(guide);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
