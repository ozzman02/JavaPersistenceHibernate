package com.hibernate.advanced._09_merge_detached_objects.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;

@Entity
public class StudentForMergedDetachedObj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name="guide_id")
    private GuideForMergedDetachedObj guide;

    public StudentForMergedDetachedObj() {}

    public StudentForMergedDetachedObj(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId; 	this.name = name;
    }

    public void setGuide(GuideForMergedDetachedObj guide) {
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
        if(!(obj instanceof StudentForMergedDetachedObj)) return false;
        StudentForMergedDetachedObj other = (StudentForMergedDetachedObj) obj;
        return new EqualsBuilder().append(enrollmentId, other.enrollmentId).isEquals();
    }

    @Override
    public String toString() {
        return "Student [id=" + id + ", enrollmentId=" + enrollmentId
                + ", name=" + name + ", guide=" + guide + "]";
    }

}
