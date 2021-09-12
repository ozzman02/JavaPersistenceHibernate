package com.hibernate.advanced._09_merge_detached_objects.service;

public interface MergeDetachedObjectsService {
    void createGuidesAndStudents();
    void mergingDetachedObjects();
    void createGuideAndStudentsForExtendedPersistenceContext();
    void objectModificationsOnAnExtendedPersistenceContext();
    void createSimpleStudentData();
    void identityOfDetachedObjects();
    void usingIdForEqualsAndHashCode();
}
