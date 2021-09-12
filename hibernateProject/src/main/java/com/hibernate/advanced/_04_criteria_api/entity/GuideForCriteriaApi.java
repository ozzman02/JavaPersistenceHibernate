package com.hibernate.advanced._04_criteria_api.entity;

import com.hibernate.advanced._03_jpql.entity.Guide3;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Entity
@NamedQueries({
        @NamedQuery(name = "Guide.findAll", query = "select g from GuideForCriteriaApi g"),
        @NamedQuery(name = "Guide.findByName", query = "select g from GuideForCriteriaApi g where g.name = :name"),
        @NamedQuery(name = "Guide.findById", query = "select g from GuideForCriteriaApi g where g.id = :id")
})
public class GuideForCriteriaApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;

    private Integer salary;

    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST})
    private Set<StudentForCriteriaApi> students = new HashSet<>();

    public GuideForCriteriaApi() {}

    public GuideForCriteriaApi(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public Set<StudentForCriteriaApi> getStudents() {
        return students;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public void addStudent(StudentForCriteriaApi student) {
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
