package com.hibernate.basic._07_collection_of_value_types.client;

import com.hibernate.basic._07_collection_of_value_types.service.MappingCollectionOfValueTypesService;
import com.hibernate.basic._07_collection_of_value_types.service.impl.MappingCollectionOfValueTypesServiceImpl;

public class MappingCollectionOfValueTypesClient {

    public static void main(String[] args) {
        MappingCollectionOfValueTypesService mappingCollectionOfValueTypesService
                = new MappingCollectionOfValueTypesServiceImpl();
        mappingCollectionOfValueTypesService.createFriend();
        mappingCollectionOfValueTypesService.getFriend();
        mappingCollectionOfValueTypesService.saveItems();
        mappingCollectionOfValueTypesService.getItem();
    }

}
