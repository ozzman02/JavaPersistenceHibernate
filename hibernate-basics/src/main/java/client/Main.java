package client;

import usage.*;

public class Main {

    static void oneToManyExamples() {
        OneToMany.loadGuideAndStudents();
        OneToMany.updateStudent();
    }

    static void oneToOneExample() {
        OneToOne.createCustomerWithPassport();
    }

    static void manyToManyExample() {
        ManyToMany.createMoviesWithActors();
        ManyToMany.updateActor();
        ManyToMany.updateMovie();
    }

    static void mappingEnumsExample() {
        EmployeeUsage.createEmployee();
    }

    static void mapCollectionsOfSimpleValueTypesExample() {
        MapCollectionSimpleValueTypes.createFriendWithNicknamesAndAddress();
        MapCollectionSimpleValueTypes.getFriend();
    }

    static void compositeKeyExample() {
        CompositeKey.createParentPrimaryKey();
        CompositeKey.createParentWithChildren();
    }

    public static void main(String[] args) {

        /*
            Clean the database before running all the methods
         */

        oneToManyExamples();
        oneToOneExample();
        manyToManyExample();
        mappingEnumsExample();
        mapCollectionsOfSimpleValueTypesExample();
        compositeKeyExample();
    }

}
