package com.hibernate.advanced.preinsertidentifiergeneration.service;

public interface PreInsertIdentifierGenerationService {
    void createStudentsWithGenerationTypeSequence();
    void createStudentsWithGenerationTypeTable();
}
