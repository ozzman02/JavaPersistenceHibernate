package com.hibernate.basic.mappingenums.client;

import com.hibernate.basic.mappingenums.service.MappingEnumsService;
import com.hibernate.basic.mappingenums.service.impl.MappingEnumsServiceImpl;

public class MappingEnumsClient {
    public static void main(String[] args) {
        MappingEnumsService mappingEnumsService = new MappingEnumsServiceImpl();
        mappingEnumsService.saveEmployee();
    }
}
