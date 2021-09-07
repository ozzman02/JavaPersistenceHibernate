package com.hibernate.basic._03_one_to_one.client;

import com.hibernate.basic._03_one_to_one.service.OneToOneService;
import com.hibernate.basic._03_one_to_one.service.impl.OneToOneServiceImpl;

public class OneToOneClient {

    public static void main(String[] args) {
        OneToOneService oneToOneService = new OneToOneServiceImpl();
        oneToOneService.saveCustomersWithPassports();
        oneToOneService.updateCustomer();
        oneToOneService.deleteCustomer();
    }

}
