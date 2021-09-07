package com.hibernate.advanced._03_jpql.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Student3 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="guide_id")
    private Guide3 guide;

    public Student3() {}

    public Student3(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId;
        this.name = name;
    }

    public Student3(String enrollmentId, String name, Guide3 guide) {
        this.enrollmentId = enrollmentId;
        this.name = name;
        this.guide = guide;
    }

    public Guide3 getGuide() {
        return guide;
    }

    public void setGuide(Guide3 guide) {
        this.guide = guide;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student3.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("enrollmentId='" + enrollmentId + "'")
                .add("name='" + name + "'")
                .add("guide='" + guide + "'")
                .toString();
    }
}
