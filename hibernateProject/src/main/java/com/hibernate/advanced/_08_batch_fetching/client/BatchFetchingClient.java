package com.hibernate.advanced._08_batch_fetching.client;

import com.hibernate.advanced._08_batch_fetching.service.BatchFetchingService;
import com.hibernate.advanced._08_batch_fetching.service.impl.BatchFetchingServiceImpl;

public class BatchFetchingClient {
    public static void main(String[] args) {
        BatchFetchingService batchFetchingService = new BatchFetchingServiceImpl();
        //batchFetchingService.createStudentsAndGuides();
        batchFetchingService.displayStudentInfo();
    }
}
