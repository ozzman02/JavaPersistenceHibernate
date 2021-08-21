package com.hibernate.advanced.lazyandeagerfetching.client;

import com.hibernate.advanced.lazyandeagerfetching.service.LazyAndEagerFetchingService;
import com.hibernate.advanced.lazyandeagerfetching.service.impl.LazyAndEagerFetchingServiceImpl;

public class LazyAndEagerFetchingClient {
    public static void main(String[] args) {
        LazyAndEagerFetchingService lazyAndEagerFetchingService = new LazyAndEagerFetchingServiceImpl();
        //lazyAndEagerFetchingService.saveGuideAndStudents();
        //lazyAndEagerFetchingService.getGuide();
        //lazyAndEagerFetchingService.getStudent();
        lazyAndEagerFetchingService.equalsAndHasCode();
    }
}
