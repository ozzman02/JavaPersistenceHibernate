package com.hibernate.advanced.preinsertidentifiergeneration.client;

import com.hibernate.advanced.preinsertidentifiergeneration.service.PreInsertIdentifierGenerationService;
import com.hibernate.advanced.preinsertidentifiergeneration.service.impl.PreInsertIdentifierGenerationServiceImpl;

public class PreInsertIdentifierGenerationClient {

    public static void main(String[] args) {
        PreInsertIdentifierGenerationService preInsertIdentifierGenerationService = new PreInsertIdentifierGenerationServiceImpl();
        preInsertIdentifierGenerationService.createStudentsWithGenerationTypeSequence();
        preInsertIdentifierGenerationService.createStudentsWithGenerationTypeTable();
    }

}
