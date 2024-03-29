package com.hibernate.advanced._09_merge_detached_objects.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GuideForExtendedPersistenceContext {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staff_id", nullable = false)
    private String staffId;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy = "guide", cascade={CascadeType.PERSIST})
    private Set<StudentForExtendedPersistenceContext> students = new HashSet<>();

    public GuideForExtendedPersistenceContext() {	}

    public GuideForExtendedPersistenceContext(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<StudentForExtendedPersistenceContext> getStudents() {
        return students;
    }

    public void addStudent(StudentForExtendedPersistenceContext student) {
        students.add(student);
        student.setGuide(this);
    }

    public void addStudents(Set<StudentForExtendedPersistenceContext> students) {
        students.forEach(studentForMergedDetachedObj -> addStudent(studentForMergedDetachedObj));
    }
}
