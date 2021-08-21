package com.hibernate.basic.compositekeys.primary.client;

import com.hibernate.basic.compositekeys.primary.service.CompositeKeysService;
import com.hibernate.basic.compositekeys.primary.service.impl.CompositeKeysServiceImpl;

public class CompositeKeysClient {
    public static void main(String[] args) {
        CompositeKeysService compositeKeysService = new CompositeKeysServiceImpl();
        //compositeKeysService.saveParent();
        //compositeKeysService.getParent();
        //compositeKeysService.saveParentWithChild();
        compositeKeysService.getParentWithChild();
    }
}
