package com.hibernate.basic._06_mapping_enums.client;

import com.hibernate.basic._06_mapping_enums.service.MappingEnumsService;
import com.hibernate.basic._06_mapping_enums.service.impl.MappingEnumsServiceImpl;

public class MappingEnumsClient {
    public static void main(String[] args) {
        MappingEnumsService mappingEnumsService = new MappingEnumsServiceImpl();
        mappingEnumsService.saveEmployee();
    }
}
