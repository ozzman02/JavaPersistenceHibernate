package com.hibernate.advanced._06_pre_insert_identifier_generation.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
public class GenerationTypeTableStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "enrollment_id", nullable = false, unique = true)
    private String enrollmentId;

    public GenerationTypeTableStudent() {
    }

    public GenerationTypeTableStudent(String name, String enrollmentId) {
        this.name = name;
        this.enrollmentId = enrollmentId;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("enrollmentId", enrollmentId)
                .toString();
    }
}
