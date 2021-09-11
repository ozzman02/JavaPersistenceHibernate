package com.hibernate.advanced._08_batch_fetching.entity;

import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "GuideObjectBatch")
@BatchSize(size = 2)
public class GuideObjectBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staff_id", nullable = false)
    private String staffId;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.PERSIST)
    private Set<StudentObjectBatch> students = new HashSet<>();

    public GuideObjectBatch() {}

    public GuideObjectBatch(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<StudentObjectBatch> getStudents() {
        return students;
    }

    public void setStudents(Set<StudentObjectBatch> students) {
        this.students = students;
    }

    public void addStudent(StudentObjectBatch student) {
        this.students.add(student);
        student.setGuide(this);
    }

    public void addStudents(Set<StudentObjectBatch> students) {
        students.forEach(studentObject -> addStudent(studentObject));
    }
}
