package com.hibernate.basic._08_composite_keys.foreign.client;

import com.hibernate.basic._08_composite_keys.foreign.service.ForeignKeyInCompositePrimaryKeyService;
import com.hibernate.basic._08_composite_keys.foreign.service.impl.ForeignKeyInCompositePrimaryKeyServiceImpl;

public class ForeignKeyInCompositePrimaryKeyClient {
    public static void main(String[] args) {
        ForeignKeyInCompositePrimaryKeyService foreignKeyInCompositePrimaryKeyService
                = new ForeignKeyInCompositePrimaryKeyServiceImpl();
        foreignKeyInCompositePrimaryKeyService.saveUser();
        foreignKeyInCompositePrimaryKeyService.getUser();
    }
}
