package com.hibernate.basic._08_composite_keys.primary.service;

public interface CompositeKeysService {

    void saveParent();

    void saveParentWithChild();

    void getParent();

    void getParentWithChild();
}
