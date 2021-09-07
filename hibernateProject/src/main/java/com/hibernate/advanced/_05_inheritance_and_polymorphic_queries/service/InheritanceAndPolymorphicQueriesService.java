package com.hibernate.advanced._05_inheritance_and_polymorphic_queries.service;

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
