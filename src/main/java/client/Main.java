package client;

import usage.OneToMany;

public class Main {

    private static void oneToManyExamples() {
        OneToMany.loadGuideAndStudents();
        OneToMany.updateStudent();
    }

    public static void main(String[] args) {
        oneToManyExamples();
    }

}
