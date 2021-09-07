package com.hibernate.basic._08_composite_keys.primary.service.impl;

import com.hibernate.basic._08_composite_keys.primary.entity.Child;
import com.hibernate.basic._08_composite_keys.primary.entity.Parent;
import com.hibernate.basic._08_composite_keys.primary.entity.ParentPrimaryKey;
import com.hibernate.basic._08_composite_keys.primary.service.CompositeKeysService;
import com.hibernate.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompositeKeysServiceImpl implements CompositeKeysService {

    @Override
    public void saveParent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "King");
            Parent parent = new Parent(parentPrimaryKey);
            session.persist(parent);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveParentWithChild() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Charlotte", "Crawford");
            Parent parent = new Parent(parentPrimaryKey);
            Child child1 = new Child("Ruby");
            Child child2 = new Child("Groovy");
            parent.addChild(child1);
            parent.addChild(child2);
            session.persist(parent);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void getParent() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "King");
            Parent parent = session.get(Parent.class, parentPrimaryKey);
            System.out.println(parent);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void getParentWithChild() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Charlotte", "Crawford");
            Parent parent = session.get(Parent.class, parentPrimaryKey);
            System.out.println(parent);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

}
