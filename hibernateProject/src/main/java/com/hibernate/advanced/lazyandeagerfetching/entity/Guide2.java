package com.hibernate.advanced.lazyandeagerfetching.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Guide2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy ="guide", cascade={CascadeType.PERSIST})
    @OrderBy("name ASC")
    private Set<Student2> students = new HashSet<>();

    public Guide2() {}

    public Guide2(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public Set<Student2> getStudents() {
        return students;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void addStudent(Student2 student) {
        students.add(student);
        student.setGuide(this);
    }

}
