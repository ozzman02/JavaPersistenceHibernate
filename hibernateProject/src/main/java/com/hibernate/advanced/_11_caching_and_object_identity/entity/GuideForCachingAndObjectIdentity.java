package com.hibernate.advanced._11_caching_and_object_identity.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class GuideForCachingAndObjectIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;

    private Integer salary;

    @Column(columnDefinition = "integer DEFAULT 0", nullable = false)
    @Version
    private Integer version;

    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST})
    private Set<StudentForCachingAndObjectIdentity> students = new HashSet<>();

    public GuideForCachingAndObjectIdentity() {}

    public GuideForCachingAndObjectIdentity(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public Set<StudentForCachingAndObjectIdentity> getStudents() {
        return students;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getStaffId() {
        return staffId;
    }

    public Integer getSalary() {
        return salary;
    }

    public void addStudent(StudentForCachingAndObjectIdentity student) {
        students.add(student);
        student.setGuide(this);
    }

    public void addStudents(Set<StudentForCachingAndObjectIdentity> students) {
        students.forEach(studentForCachingAndObjectIdentity -> addStudent(studentForCachingAndObjectIdentity));
    }

}
