package com.hibernate.basic.collectionofvaluetypes.client;

import com.hibernate.basic.collectionofvaluetypes.service.MappingCollectionOfValueTypesService;
import com.hibernate.basic.collectionofvaluetypes.service.impl.MappingCollectionOfValueTypesServiceImpl;

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
