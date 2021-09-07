package com.hibernate.basic._01_component_mapping.client;

import com.hibernate.basic._01_component_mapping.service.ComponentMappingService;
import com.hibernate.basic._01_component_mapping.service.impl.ComponentMappingServiceImpl;

public class ComponentMappingClient {
    public static void main(String[] args) {
        ComponentMappingService service = new ComponentMappingServiceImpl();
        service.savePerson();
    }
}
