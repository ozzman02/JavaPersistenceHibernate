package com.hibernate.advanced._07_selects_problem.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "Guide_Object")
public class GuideObject {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST})
    private Set<StudentObject> students = new LinkedHashSet<>();

    public GuideObject() {}

    public GuideObject(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    public Set<StudentObject> getStudents() {
        return students;
    }

    public void addStudent(StudentObject student) {
        students.add(student);
        student.setGuide(this);
    }

    public void addStudents(Set<StudentObject> students) {
        students.forEach(studentObject -> addStudent(studentObject));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("staffId", staffId)
                .append("name", name)
                .append("salary", salary)
                .toString();
    }
}
