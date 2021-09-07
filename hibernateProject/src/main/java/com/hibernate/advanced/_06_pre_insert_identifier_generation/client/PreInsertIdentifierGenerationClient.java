package com.hibernate.advanced._06_pre_insert_identifier_generation.client;

import com.hibernate.advanced._06_pre_insert_identifier_generation.service.PreInsertIdentifierGenerationService;
import com.hibernate.advanced._06_pre_insert_identifier_generation.service.impl.PreInsertIdentifierGenerationServiceImpl;

public class PreInsertIdentifierGenerationClient {

    public static void main(String[] args) {
        PreInsertIdentifierGenerationService preInsertIdentifierGenerationService = new PreInsertIdentifierGenerationServiceImpl();
        preInsertIdentifierGenerationService.createStudentsWithGenerationTypeSequence();
        preInsertIdentifierGenerationService.createStudentsWithGenerationTypeTable();
    }

}
