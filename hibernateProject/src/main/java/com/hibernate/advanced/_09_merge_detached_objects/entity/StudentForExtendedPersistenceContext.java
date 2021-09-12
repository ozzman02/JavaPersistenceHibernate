package com.hibernate.advanced._09_merge_detached_objects.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
public class StudentForExtendedPersistenceContext {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="guide_id")
    private GuideForExtendedPersistenceContext guide;

    public StudentForExtendedPersistenceContext() {}

    public StudentForExtendedPersistenceContext(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId; 	this.name = name;
    }

    public void setGuide(GuideForExtendedPersistenceContext guide) {
        this.guide = guide;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(enrollmentId).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof StudentForExtendedPersistenceContext)) return false;
        StudentForExtendedPersistenceContext other = (StudentForExtendedPersistenceContext) obj;
        return new EqualsBuilder().append(enrollmentId, other.enrollmentId).isEquals();
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", enrollmentId=" + enrollmentId
                + ", name=" + name + ", guide=" + guide + "]";
    }
}
