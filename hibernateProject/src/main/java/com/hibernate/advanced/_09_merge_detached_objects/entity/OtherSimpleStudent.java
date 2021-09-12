package com.hibernate.advanced._09_merge_detached_objects.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class OtherSimpleStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name="enrollment_id", nullable = false, unique = true)
    private String enrollmentId;

    public OtherSimpleStudent() {}

    public OtherSimpleStudent(String enrollmentId, String name) {
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
        return Objects.hash(id);
    }

    /*@Override
    public int hashCode() {
        return 31;
    }*/

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        OtherSimpleStudent other = (OtherSimpleStudent) obj;
        return Objects.equals(id, other.id);
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", enrollmentId=" + enrollmentId + ", name=" + name + "]";
    }

}
