package com.hibernate.advanced.lazyandeagerfetching.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class Student2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(cascade={CascadeType.PERSIST})
    @JoinColumn(name="guide_id")
    private Guide2 guide;

    public Student2() {}

    public Student2(String enrollmentId, String name, Guide2 guide) {
        this.enrollmentId = enrollmentId;
        this.name = name;
        this.guide = guide;
    }

    public Guide2 getGuide() {
        return guide;
    }

    public void setGuide(Guide2 guide) {
        this.guide = guide;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(enrollmentId).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Student2)) return false;
        Student2 other = (Student2) obj;
        return new EqualsBuilder().append(enrollmentId, other.enrollmentId).isEquals();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student2.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("enrollmentId='" + enrollmentId + "'")
                .add("name='" + name + "'")
                .toString();
    }
}
