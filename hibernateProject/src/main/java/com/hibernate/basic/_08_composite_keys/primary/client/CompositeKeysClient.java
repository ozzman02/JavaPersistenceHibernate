package com.hibernate.basic._08_composite_keys.primary.client;

import com.hibernate.basic._08_composite_keys.primary.service.CompositeKeysService;
import com.hibernate.basic._08_composite_keys.primary.service.impl.CompositeKeysServiceImpl;

public class CompositeKeysClient {
    public static void main(String[] args) {
        CompositeKeysService compositeKeysService = new CompositeKeysServiceImpl();
        compositeKeysService.saveParent();
        compositeKeysService.getParent();
        compositeKeysService.saveParentWithChild();
        compositeKeysService.getParentWithChild();
    }
}
