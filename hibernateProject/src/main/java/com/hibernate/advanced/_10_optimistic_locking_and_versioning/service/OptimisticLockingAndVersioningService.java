package com.hibernate.advanced._10_optimistic_locking_and_versioning.service;

public interface OptimisticLockingAndVersioningService {
    void loadGuidesAndStudents();
    void updateGuideSalaryConcurrently();
    void pessimisticReadLockExample();
    void pessimisticWriteLockExample();
}
