package com.hibernate.basic.componentmapping.client;

import com.hibernate.basic.componentmapping.service.ComponentMappingService;
import com.hibernate.basic.componentmapping.service.impl.ComponentMappingServiceImpl;

public class ComponentMappingClient {
    public static void main(String[] args) {
        ComponentMappingService service = new ComponentMappingServiceImpl();
        service.savePerson();
    }
}
