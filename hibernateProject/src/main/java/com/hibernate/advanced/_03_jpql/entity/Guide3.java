package com.hibernate.advanced._03_jpql.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@NamedQueries({
        @NamedQuery(name = "Guide.findAll", query = "select g from Guide3 g"),
        @NamedQuery(name = "Guide.findByName", query = "select g from Guide3 g where g.name = :name"),
        @NamedQuery(name = "Guide.findById", query = "select g from Guide3 g where g.id = :id")
})
public class Guide3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST})
    private Set<Student3> students = new HashSet<>();

    public Guide3() {}

    public Guide3(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public Set<Student3> getStudents() {
        return students;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void addStudent(Student3 student) {
        students.add(student);
        student.setGuide(this);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Guide3.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("staffId='" + staffId + "'")
                .add("name='" + name + "'")
                .add("salary=" + salary)
                .toString();
    }
}
