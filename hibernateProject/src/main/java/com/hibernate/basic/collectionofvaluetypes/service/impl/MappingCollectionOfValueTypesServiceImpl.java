package com.hibernate.basic.collectionofvaluetypes.service.impl;

import com.hibernate.basic.collectionofvaluetypes.entity.Friend;
import com.hibernate.basic.collectionofvaluetypes.entity.FriendAddress;
import com.hibernate.basic.collectionofvaluetypes.entity.Item;
import com.hibernate.basic.collectionofvaluetypes.service.MappingCollectionOfValueTypesService;
import com.hibernate.basic.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MappingCollectionOfValueTypesServiceImpl implements MappingCollectionOfValueTypesService {

    @Override
    public void createFriend() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Friend friend = new Friend("Mark Anderson", "markanderson@plushwhere.com");
            friend.getNickNames().add("Marky");
            friend.getNickNames().add("Marco");
            friend.getNickNames().add("Markster");
            friend.getAddresses().add(new FriendAddress("street1", "city1", "zipcode1"));
            friend.getAddresses().add(new FriendAddress("street2", "city2", "zipcode2"));
            friend.getAddresses().add(new FriendAddress("street3", "city3", "zipcode3"));
            session.persist(friend);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void getFriend() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Friend friend = session.get(Friend.class, 1L);
            System.out.println(friend);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void saveItems() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Item foo = new Item("Foo");
            foo.getImages().add("foo.jpg");
            foo.getImages().add("bar.jpg");
            foo.getImages().add("baz.png");
            Item b = new Item("B");
            b.getImages().add("b.jpg");
            Item c = new Item("C");
            c.getImages().add("c.png");
            session.persist(foo);
            session.persist(b);
            session.persist(c);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void getItem() {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();
            Item item = session.get(Item.class, 1L);
            System.out.println(item);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

}
