package usage;

import entity.Child;
import entity.Parent;
import entity.ParentPrimaryKey;
import org.hibernate.Session;
import util.HibernateUtil;

public class CompositeKey {

    public static void createParentPrimaryKey(){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Gavin", "King");

        Parent parent = new Parent(parentPrimaryKey);

        session.persist(parent);

        session.getTransaction().commit();

        session.close();

    }

    public static void createParentWithChildren() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        ParentPrimaryKey parentPrimaryKey = new ParentPrimaryKey("Charlotte", "Crawford");

        Parent parent = new Parent(parentPrimaryKey);

        Child child1 = new Child("Ruby");
        Child child2 = new Child("Groovy");

        parent.addChild(child1);
        parent.addChild(child2);

        session.persist(parent);

        session.getTransaction().commit();
        session.close();

    }
}
