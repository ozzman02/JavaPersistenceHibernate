package com.hibernate.advanced._11_caching_and_object_identity.client;

import com.hibernate.advanced._11_caching_and_object_identity.service.CachingAndObjectIdentityService;
import com.hibernate.advanced._11_caching_and_object_identity.service.impl.CachingAndObjectIdentityServiceImpl;

public class CachingAndObjectIdentityClient {
    public static void main(String[] args) {
        CachingAndObjectIdentityService cachingAndObjectIdentityService = new CachingAndObjectIdentityServiceImpl();
        cachingAndObjectIdentityService.loadGuidesAndStudents();
        cachingAndObjectIdentityService.cachingAndObjectIdentityExample();
        cachingAndObjectIdentityService.cachingAndObjectIdentityExample2();
    }
}
