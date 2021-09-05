package com.hibernate.advanced.inheritanceandpolymorphicqueries.service;

public interface InheritanceAndPolymorphicQueriesService {
    void persistSingleTableAnimals();
    void singleTableStrategyPolymorphicQuery();
    void displaySingleTableDogs();
    void persistJoinedAnimals();
    void joinedStrategyPolymorphicQuery();
    void displayJoinedDogs();
    void persistTablePerClassAnimals();
    void tablePerClassStrategyPolymorphicQuery();
    void displayTablePerClassDogs();
    void persistAccounts();
}
