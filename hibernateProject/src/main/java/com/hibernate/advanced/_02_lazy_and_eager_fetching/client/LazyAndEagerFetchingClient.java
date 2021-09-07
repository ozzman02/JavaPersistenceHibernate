package com.hibernate.advanced._02_lazy_and_eager_fetching.client;

import com.hibernate.advanced._02_lazy_and_eager_fetching.service.LazyAndEagerFetchingService;
import com.hibernate.advanced._02_lazy_and_eager_fetching.service.impl.LazyAndEagerFetchingServiceImpl;

public class LazyAndEagerFetchingClient {
    public static void main(String[] args) {
        LazyAndEagerFetchingService lazyAndEagerFetchingService = new LazyAndEagerFetchingServiceImpl();
        lazyAndEagerFetchingService.saveGuideAndStudents();
        lazyAndEagerFetchingService.getGuide();
        lazyAndEagerFetchingService.getStudent();
        lazyAndEagerFetchingService.equalsAndHasCode();
    }
}
