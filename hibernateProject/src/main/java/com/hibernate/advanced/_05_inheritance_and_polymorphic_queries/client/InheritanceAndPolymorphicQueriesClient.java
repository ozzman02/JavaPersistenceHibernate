package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.client;

import com.hibernate.advanced._05_inheritance_and_polymorphic_queries.service.InheritanceAndPolymorphicQueriesService;
import com.hibernate.advanced._05_inheritance_and_polymorphic_queries.service.impl.InheritanceAndPolymorphicQueriesServiceImpl;

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
