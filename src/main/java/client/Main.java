package client;

import usage.*;

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

    private static void mapCollectionsOfSimpleValueTypesExample() {
        MapCollectionSimpleValueTypes.createFriendWithNicknamesAndAddress();
        MapCollectionSimpleValueTypes.getFriend();
    }

    private static void compositeKeyExample() {
        //CompositeKey.createParentPrimaryKey();
        CompositeKey.createParentWithChildren();
    }

    public static void main(String[] args) {

        /*
            Clean the database before running all the methods
         */

        //oneToManyExamples();
        //oneToOneExample();
        //manyToManyExample();
        //mappingEnumsExample();
        //mapCollectionsOfSimpleValueTypesExample();
        compositeKeyExample();
    }

}
