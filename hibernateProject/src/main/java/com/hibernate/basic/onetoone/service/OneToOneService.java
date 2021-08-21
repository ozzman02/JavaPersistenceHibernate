package com.hibernate.basic.onetoone.service;

public interface OneToOneService {

    void clearCustomersAndPassports();

    void saveCustomersWithPassports();

    void updateCustomer();

    void deleteCustomer();

}
