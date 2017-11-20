package usage;

import entity.Employee;
import entity.EmployeeStatus;
import org.hibernate.Session;
import util.HibernateUtil;

public class EmployeeUsage {

    public static void createEmployee() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Employee employee1 = new Employee("John Stockham", "2014JA11001", EmployeeStatus.FULL_TIME);
        Employee employee2 = new Employee("Ammie Corrio", "2014AI21543", EmployeeStatus.PART_TIME);
        Employee employee3 = new Employee("Ernie Stenseth", "2014ET25100", EmployeeStatus.CONTRACT);

        session.persist(employee1);
        session.persist(employee2);
        session.persist(employee3);

        session.getTransaction().commit();
        session.close();

    }

}
