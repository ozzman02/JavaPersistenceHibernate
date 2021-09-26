package com.hibernate.advanced._12_second_level_cache.entity;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.annotations.Cache;

@Entity
@Cacheable
public class GuideForSecondLevelCache {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    @Column(name="staff_id", nullable=false)
    private String staffId;

    private String name;

    private Integer salary;

    @Version
    private Integer version;

    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy="guide", cascade={CascadeType.PERSIST})
    private Set<StudentForSecondLevelCache> students = new HashSet<>();

    public GuideForSecondLevelCache() {}

    public GuideForSecondLevelCache(String staffId, String name, Integer salary) {
        this.staffId = staffId;
        this.name = name;
        this.salary = salary;
    }

    public Set<StudentForSecondLevelCache> getStudents() {
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

    public void addStudent(StudentForSecondLevelCache student) {
        students.add(student);
        student.setGuide(this);
    }

    public void addStudents(Set<StudentForSecondLevelCache> students) {
        students.forEach(studentForSecondLevelCache -> addStudent(studentForSecondLevelCache));
    }

}
