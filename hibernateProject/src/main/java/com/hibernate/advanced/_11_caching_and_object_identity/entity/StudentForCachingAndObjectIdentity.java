package com.hibernate.advanced._11_caching_and_object_identity.entity;

import javax.persistence.*;

@Entity
public class StudentForCachingAndObjectIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="guide_id")
    private GuideForCachingAndObjectIdentity guide;

    public StudentForCachingAndObjectIdentity() {}

    public StudentForCachingAndObjectIdentity(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId;
        this.name = name;
    }

    public GuideForCachingAndObjectIdentity getGuide() {
        return guide;
    }

    public void setGuide(GuideForCachingAndObjectIdentity guide) {
        this.guide = guide;
    }

    public String getName() {
        return name;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

}
