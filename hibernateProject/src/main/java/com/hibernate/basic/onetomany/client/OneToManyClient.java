package com.hibernate.basic.onetomany.client;

import com.hibernate.basic.onetomany.service.OneToManyService;
import com.hibernate.basic.onetomany.service.impl.OneToManyServiceImpl;

public class OneToManyClient {

    public static void main(String[] args) {
        OneToManyService oneToManyService = new OneToManyServiceImpl();
        oneToManyService.saveGuideAndStudents();
        oneToManyService.updateStudent();
        oneToManyService.deleteStudent();
        oneToManyService.deleteGuide();
    }

}
