package com.hibernate.advanced.criteriaapi.client;

import com.hibernate.advanced.criteriaapi.service.CriteriaApiService;
import com.hibernate.advanced.criteriaapi.service.impl.CriteriaApiServiceImpl;

public class CriteriaApiClient {

    public static void main(String[] args) {
        CriteriaApiService criteriaApiService = new CriteriaApiServiceImpl();
        //criteriaApiService.getGuide();
        //criteriaApiService.getGuideNames();
        //criteriaApiService.getMultipleGuideFields();
        //criteriaApiService.filteringResults();
        //criteriaApiService.findGuideByName("Ian Lamb");
        //criteriaApiService.findGuideByStaffId();
        //criteriaApiService.countGuides();
        //criteriaApiService.getGuideMaxSalary();
        //criteriaApiService.getGuideByName("Ian Lamb");
        //criteriaApiService.printGuideByName("Mike Lawson");
        //criteriaApiService.findAllGuides();
        //criteriaApiService.findGuideById(2L);
        // criteriaApiService.getAllGuidesNativeQuery();
        // criteriaApiService.innerJoin();
        // criteriaApiService.innerJoin2();
        //criteriaApiService.leftJoin();
        //criteriaApiService.innerJoinFetch();
        // criteriaApiService.leftJoinFetch();
        criteriaApiService.fetchingDistinctResults();
    }

}
