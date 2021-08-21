package com.hibernate.advanced.hibernatejpaprovider.client;

import com.hibernate.advanced.hibernatejpaprovider.service.HibernateAsJpaProviderService;
import com.hibernate.advanced.hibernatejpaprovider.service.impl.HibernateAsJpaProviderServiceImpl;

public class HibernateAsJpaProviderClient {
    public static void main(String[] args) {
        HibernateAsJpaProviderService hibernateAsJpaProviderService = new HibernateAsJpaProviderServiceImpl();
        hibernateAsJpaProviderService.saveMessage();
    }
}
