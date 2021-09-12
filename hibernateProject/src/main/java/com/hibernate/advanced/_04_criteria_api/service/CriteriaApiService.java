package com.hibernate.advanced._04_criteria_api.service;

public interface CriteriaApiService {

    void saveStudentsAndGuides();

    void getGuide();

    void getGuideNames();

    void getMultipleGuideFields();

    void filteringResults();

    void findGuideByName(String name);

    void findGuideByStaffId();

    void countGuides();

    void getGuideMaxSalary();

    void getGuideByName(String name);

    void printGuideByName(String name);

    void findAllGuides();

    void findGuideById(Long id);

    void getAllGuidesNativeQuery();

    void innerJoin();

    void innerJoin2();

    void leftJoin();

    void innerJoinFetch();

    void leftJoinFetch();

    void fetchingDistinctResults();

}
