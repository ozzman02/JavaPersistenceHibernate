package com.hibernate.advanced._07_selects_problem.client;

import com.hibernate.advanced._07_selects_problem.service.SelectsProblemService;
import com.hibernate.advanced._07_selects_problem.service.impl.SelectsProblemServiceImpl;

/* N+1 Selects Problem */
public class SelectsProblemClient {
    public static void main(String[] args) {
        SelectsProblemService selectsProblemService = new SelectsProblemServiceImpl();
        selectsProblemService.createGuidesAndStudents();
        selectsProblemService.displayStudentNameAndEnrollmentId();
        selectsProblemService.displayStudentNameEnrollmentIdAndGuideName();
    }
}
