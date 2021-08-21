package com.hibernate.basic.compositekeys.primary.service;

public interface CompositeKeysService {

    void saveParent();

    void saveParentWithChild();

    void getParent();

    void getParentWithChild();
}
