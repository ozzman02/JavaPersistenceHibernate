package com.hibernate.advanced.jpql.client;

import com.hibernate.advanced.jpql.service.JpqlService;
import com.hibernate.advanced.jpql.service.impl.JpqlServiceImpl;

public class JpqlClient {
    public static void main(String[] args) {
        JpqlService jpqlService = new JpqlServiceImpl();
        jpqlService.saveStudentsAndGuides();
        jpqlService.listGuidesReport();
        jpqlService.getGuideBySalary();
        jpqlService.listGuides();
        jpqlService.getGuideByName("Ian Lamb");
        jpqlService.getGuideByNameWithNamedParameter("Ian Lamb");
        jpqlService.getGuideByNameUsingLikePattern();
        jpqlService.listGuidesWithNativeQuery();
        jpqlService.findGuideByName("Mike Lawson");
        jpqlService.getNumberOfGuides();
        jpqlService.countGuides();
        jpqlService.getMaxSalary();
        jpqlService.innerJoin();
        jpqlService.leftJoin();
        jpqlService.rightJoin();
        jpqlService.joinFetch();
        jpqlService.studentsWithNoGuideReport();
        jpqlService.guidesWithNoStudentsReport();
        jpqlService.guidesWithAtLeastOneStudent();
        jpqlService.flushingWithDefault();
        jpqlService.flushingWithCommitMode();
    }
}
