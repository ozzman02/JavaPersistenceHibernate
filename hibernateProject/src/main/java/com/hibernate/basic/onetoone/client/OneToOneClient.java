package com.hibernate.basic.onetoone.client;

import com.hibernate.basic.onetoone.service.OneToOneService;
import com.hibernate.basic.onetoone.service.impl.OneToOneServiceImpl;

public class OneToOneClient {

    public static void main(String[] args) {
        OneToOneService oneToOneService = new OneToOneServiceImpl();
        oneToOneService.saveCustomersWithPassports();
        oneToOneService.updateCustomer();
        oneToOneService.deleteCustomer();
    }

}
