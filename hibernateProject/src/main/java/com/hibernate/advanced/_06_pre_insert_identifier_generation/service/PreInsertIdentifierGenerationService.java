package com.hibernate.advanced._06_pre_insert_identifier_generation.service;

public interface PreInsertIdentifierGenerationService {
    void createStudentsWithGenerationTypeSequence();
    void createStudentsWithGenerationTypeTable();
}
