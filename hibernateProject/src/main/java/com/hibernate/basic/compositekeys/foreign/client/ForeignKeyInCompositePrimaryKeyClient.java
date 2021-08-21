package com.hibernate.basic.compositekeys.foreign.client;

import com.hibernate.basic.compositekeys.foreign.service.ForeignKeyInCompositePrimaryKeyService;
import com.hibernate.basic.compositekeys.foreign.service.impl.ForeignKeyInCompositePrimaryKeyServiceImpl;

public class ForeignKeyInCompositePrimaryKeyClient {
    public static void main(String[] args) {
        ForeignKeyInCompositePrimaryKeyService foreignKeyInCompositePrimaryKeyService
                = new ForeignKeyInCompositePrimaryKeyServiceImpl();
        foreignKeyInCompositePrimaryKeyService.saveUser();
        foreignKeyInCompositePrimaryKeyService.getUser();
    }
}
