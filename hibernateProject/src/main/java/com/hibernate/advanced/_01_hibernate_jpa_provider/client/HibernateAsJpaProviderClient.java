package com.hibernate.advanced._01_hibernate_jpa_provider.client;

import com.hibernate.advanced._01_hibernate_jpa_provider.service.HibernateAsJpaProviderService;
import com.hibernate.advanced._01_hibernate_jpa_provider.service.impl.HibernateAsJpaProviderServiceImpl;

public class HibernateAsJpaProviderClient {
    public static void main(String[] args) {
        HibernateAsJpaProviderService hibernateAsJpaProviderService = new HibernateAsJpaProviderServiceImpl();
        hibernateAsJpaProviderService.saveMessage();
    }
}
