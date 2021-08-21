package com.hibernate.advanced.jpql.service;

public interface JpqlService {
    void saveStudentsAndGuides();
    void listGuides();
    void getGuideBySalary();
    void listGuidesReport();
    void getGuideByName(String name);
    void getGuideByNameWithNamedParameter(String name);
    void getGuideByNameUsingLikePattern();
    void listGuidesWithNativeQuery();
    void findGuideByName(String name);
    void getNumberOfGuides();
    void countGuides();
    void getMaxSalary();
    void innerJoin();
    void leftJoin();
    void rightJoin();
    void joinFetch();
    void studentsWithNoGuideReport();
    void guidesWithNoStudentsReport();
    void guidesWithAtLeastOneStudent();
    void flushingWithDefault();
    void flushingWithCommitMode();
}
