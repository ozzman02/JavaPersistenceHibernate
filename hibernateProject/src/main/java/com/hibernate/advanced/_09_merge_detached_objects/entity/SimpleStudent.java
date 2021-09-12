package com.hibernate.advanced._09_merge_detached_objects.entity;

import javax.persistence.*;

@Entity
public class SimpleStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name="enrollment_id", nullable = false, unique = true)
    private String enrollmentId;

    public SimpleStudent() {}

    public SimpleStudent(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return this.getEnrollmentId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof SimpleStudent)) return false;
        SimpleStudent other = (SimpleStudent) obj;
        return this.getEnrollmentId().equals(other.getEnrollmentId());
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", enrollmentId=" + enrollmentId + ", name=" + name + "]";
    }
}
