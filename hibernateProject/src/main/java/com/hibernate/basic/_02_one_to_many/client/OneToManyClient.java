package com.hibernate.basic._02_one_to_many.client;

import com.hibernate.basic._02_one_to_many.service.OneToManyService;
import com.hibernate.basic._02_one_to_many.service.impl.OneToManyServiceImpl;

public class OneToManyClient {

    public static void main(String[] args) {
        OneToManyService oneToManyService = new OneToManyServiceImpl();
        oneToManyService.saveGuideAndStudents();
        oneToManyService.updateStudent();
        oneToManyService.deleteStudent();
        oneToManyService.deleteGuide();
    }

}
