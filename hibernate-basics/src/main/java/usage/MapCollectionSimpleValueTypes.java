package usage;

import entity.Address;
import entity.Friend;
import org.hibernate.Session;
import util.HibernateUtil;

public class MapCollectionSimpleValueTypes {

    public static void createFriendWithNicknamesAndAddress() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Friend friend = new Friend("Mark Anderson", "markanderson@plushwhere.com");

        Address address1 = new Address("zipcode1","street1","city1");
        Address address2 = new Address("zipcode2","street2","city2");
        Address address3 = new Address("zipcode3","street3","city3");

        friend.getNicknames().add("Marky");
        friend.getNicknames().add("Marco");
        friend.getNicknames().add("Markster");

        friend.getAddresses().add(address1);
        friend.getAddresses().add(address2);
        friend.getAddresses().add(address3);

        session.persist(friend);

        session.getTransaction().commit();

        session.close();

    }

    public static void getFriend() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Friend friend = (Friend) session.get(Friend.class, 1L);
        System.out.println(friend);

        session.getTransaction().commit();
        session.close();

    }


}
