package com.hibernate.advanced._10_optimistic_locking_and_versioning.client;

import com.hibernate.advanced._10_optimistic_locking_and_versioning.service.OptimisticLockingAndVersioningService;
import com.hibernate.advanced._10_optimistic_locking_and_versioning.service.impl.OptimisticLockingAndVersioningServiceImpl;

public class OptimisticLockingAndVersioningClient {
    public static void main(String[] args) {
        OptimisticLockingAndVersioningService optimisticLockingAndVersioningService = new OptimisticLockingAndVersioningServiceImpl();
        optimisticLockingAndVersioningService.loadGuidesAndStudents();
        optimisticLockingAndVersioningService.updateGuideSalaryConcurrently();
        optimisticLockingAndVersioningService.pessimisticReadLockExample();
        optimisticLockingAndVersioningService.pessimisticWriteLockExample();
        optimisticLockingAndVersioningService.isolationRuleExample();
    }
}
