package com.hibernate.advanced._12_second_level_cache.client;

import com.hibernate.advanced._12_second_level_cache.service.SecondLevelCacheService;
import com.hibernate.advanced._12_second_level_cache.service.impl.SecondLevelCacheServiceImpl;

public class SecondLevelCacheClient {
    public static void main(String[] args) {
        SecondLevelCacheService secondLevelCacheService = new SecondLevelCacheServiceImpl();
        secondLevelCacheService.createGuideAndStudents();
        secondLevelCacheService.secondLevelCachingExample();
        secondLevelCacheService.statisticsExample();
        secondLevelCacheService.cachingAssociations();
        secondLevelCacheService.cachingCollections();
    }
}
