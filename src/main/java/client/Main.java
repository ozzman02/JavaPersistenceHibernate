package client;

import usage.EmployeeUsage;
import usage.ManyToMany;
import usage.OneToMany;
import usage.OneToOne;

public class Main {

    private static void oneToManyExamples() {
        OneToMany.loadGuideAndStudents();
        OneToMany.updateStudent();
    }

    private static void oneToOneExample() {
        OneToOne.createCustomerWithPassport();
    }

    private static void manyToManyExample() {
        ManyToMany.createMoviesWithActors();
        ManyToMany.updateActor();
        ManyToMany.updateMovie();
    }

    private static void mappingEnumsExample() {
        EmployeeUsage.createEmployee();
    }

    public static void main(String[] args) {
        //oneToManyExamples();
        //oneToOneExample();
        //manyToManyExample();
        mappingEnumsExample();
    }

}
