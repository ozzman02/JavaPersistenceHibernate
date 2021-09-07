package com.hibernate.basic._02_one_to_many.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Guide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;

    private Integer salary;

    /* inverse end */
    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Student> students = new HashSet<Student>();

    public Guide() {}

    public Guide(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public void addStudent(Student student) {
        students.add(student);
        student.setGuide(this);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.setGuide(null);
    }

}
