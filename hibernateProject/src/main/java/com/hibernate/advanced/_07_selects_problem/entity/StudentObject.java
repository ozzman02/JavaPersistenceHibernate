package com.hibernate.advanced._07_selects_problem.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "Student_Object")
public class StudentObject {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;

    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    @JoinColumn(name="guide_id")
    private GuideObject guide;

    public StudentObject() {}

    public StudentObject(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId; 	this.name = name;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public String getName() {
        return name;
    }

    public GuideObject getGuide() {
        return guide;
    }

    public void setGuide(GuideObject guide) {
        this.guide = guide;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(enrollmentId).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StudentObject)) return false;
        StudentObject other = (StudentObject) obj;
        return new EqualsBuilder().append(enrollmentId, other.enrollmentId).isEquals();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("enrollmentId", enrollmentId)
                .append("name", name)
                .append("guide", guide)
                .toString();
    }
}
