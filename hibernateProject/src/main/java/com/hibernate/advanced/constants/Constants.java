package com.hibernate.advanced.constants;

public class Constants {

    public static final String PERSISTENCE_UNIT_NAME = "hibernate-jpa";

    public static final String LIST_GUIDES = "select guide from Guide3 as guide";

    public static final String GET_GUIDE_BY_SALARY
            = "select guide from Guide3 guide where guide.salary = 1000";

    public static final String LIST_GUIDES_REPORT
            = "select guide.name, guide.salary from Guide3 as guide";

    public static final String LIST_GUIDES_NATIVE_QUERY = "select * from guide3";

    public static final String FIND_GUIDE_BY_NAME = "findGuideByName";

    public static final String COUNT_GUIDES_QUERY = "select count(guide) from Guide3 guide";

    public static final String GET_MAX_SALARY_QUERY = "select max(guide.salary) from Guide3 guide";

    public static final String INNER_JOIN_QUERY
            = "select student from Student3 student join student.guide guide";

    public static final String LEFT_JOIN_QUERY
            = "select student from Student3 student left join student.guide guide";

    public static final String RIGHT_JOIN_QUERY
            = "select student from Student3 student right join student.guide guide";

    public static final String JOIN_FETCH_QUERY
            = "select guide from Guide3 guide join fetch guide.students student";

    public static final String STUDENTS_WITH_NO_GUIDE_QUERY
            = "select student.name, student.enrollmentId from Student3 student where student.guide is null";

    public static final String GUIDES_WITH_NO_STUDENTS_QUERY
            = "select g.name, g.staffId from Guide3 g where size(g.students) is 0";

    public static final String GUIDES_WITH_AT_LEAST_ONE_STUDENT_QUERY
            = "select g from Guide3 g join g.students s where s.name like 'A%'";

}
