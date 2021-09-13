package com.hibernate.advanced._10_optimistic_locking_and_versioning.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GuideForOptimisticLocking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "staff_id", nullable = false)
    private String staffId;

    private String name;

    private Integer salary;

    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    @Version
    private Integer version;

    @OneToMany(mappedBy = "guide", cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Set<StudentForOptimisticLocking> students = new HashSet<>();

    public GuideForOptimisticLocking() {}

    public GuideForOptimisticLocking(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public void addStudent(StudentForOptimisticLocking student) {
        students.add(student);
        student.setGuide(this);
    }

    public void addStudents(Set<StudentForOptimisticLocking> students) {
        students.forEach(studentForOptimisticLocking -> addStudent(studentForOptimisticLocking));
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Set<StudentForOptimisticLocking> getStudents() {
        return students;
    }

}
