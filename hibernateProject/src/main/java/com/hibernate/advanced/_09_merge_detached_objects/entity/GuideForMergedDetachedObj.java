package com.hibernate.advanced._09_merge_detached_objects.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GuideForMergedDetachedObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staff_id", nullable = false)
    private String staffId;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy = "guide", cascade={CascadeType.PERSIST})
    private Set<StudentForMergedDetachedObj> students = new HashSet<>();

    public GuideForMergedDetachedObj() {	}

    public GuideForMergedDetachedObj(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<StudentForMergedDetachedObj> getStudents() {
        return students;
    }

    public void addStudent(StudentForMergedDetachedObj student) {
        students.add(student);
        student.setGuide(this);
    }

    public void addStudents(Set<StudentForMergedDetachedObj> students) {
        students.forEach(studentForMergedDetachedObj -> addStudent(studentForMergedDetachedObj));
    }

}
