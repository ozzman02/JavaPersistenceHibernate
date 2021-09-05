package com.hibernate.advanced.inheritanceandpolymorphicqueries.client;

import com.hibernate.advanced.inheritanceandpolymorphicqueries.service.InheritanceAndPolymorphicQueriesService;
import com.hibernate.advanced.inheritanceandpolymorphicqueries.service.impl.InheritanceAndPolymorphicQueriesServiceImpl;

public class InheritanceAndPolymorphicQueriesClient {

    public static void main(String[] args) {
        InheritanceAndPolymorphicQueriesService inheritanceAndPolymorphicQueriesService = new InheritanceAndPolymorphicQueriesServiceImpl();
        inheritanceAndPolymorphicQueriesService.persistSingleTableAnimals();
        inheritanceAndPolymorphicQueriesService.singleTableStrategyPolymorphicQuery();
        inheritanceAndPolymorphicQueriesService.displaySingleTableDogs();
        inheritanceAndPolymorphicQueriesService.persistJoinedAnimals();
        inheritanceAndPolymorphicQueriesService.joinedStrategyPolymorphicQuery();
        inheritanceAndPolymorphicQueriesService.displayJoinedDogs();
        inheritanceAndPolymorphicQueriesService.persistTablePerClassAnimals();
        inheritanceAndPolymorphicQueriesService.tablePerClassStrategyPolymorphicQuery();
        inheritanceAndPolymorphicQueriesService.displayTablePerClassDogs();
        inheritanceAndPolymorphicQueriesService.persistAccounts();
    }

}
