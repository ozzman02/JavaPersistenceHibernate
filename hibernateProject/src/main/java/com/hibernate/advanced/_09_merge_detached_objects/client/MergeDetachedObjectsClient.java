package com.hibernate.advanced._09_merge_detached_objects.client;

import com.hibernate.advanced._09_merge_detached_objects.service.MergeDetachedObjectsService;
import com.hibernate.advanced._09_merge_detached_objects.service.impl.MergeDetachedObjectsServiceImpl;

public class MergeDetachedObjectsClient {
    public static void main(String[] args) {
        MergeDetachedObjectsService mergeDetachedObjectsService = new MergeDetachedObjectsServiceImpl();
        //mergeDetachedObjectsService.createGuidesAndStudents();
        mergeDetachedObjectsService.mergingDetachedObjects();
        //mergeDetachedObjectsService.createGuideAndStudentsForExtendedPersistenceContext();
        mergeDetachedObjectsService.objectModificationsOnAnExtendedPersistenceContext();
        //mergeDetachedObjectsService.createSimpleStudentData();
        mergeDetachedObjectsService.identityOfDetachedObjects();
        mergeDetachedObjectsService.usingIdForEqualsAndHashCode();
    }
}
