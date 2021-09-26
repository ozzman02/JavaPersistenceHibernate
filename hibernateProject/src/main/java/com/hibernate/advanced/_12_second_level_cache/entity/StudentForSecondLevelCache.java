package com.hibernate.advanced._12_second_level_cache.entity;

import javax.persistence.*;

@Entity
@Cacheable
public class StudentForSecondLevelCache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="guide_id")
    private GuideForSecondLevelCache guide;

    public StudentForSecondLevelCache() {}

    public StudentForSecondLevelCache(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId;
        this.name = name;
    }

    public GuideForSecondLevelCache getGuide() {
        return guide;
    }

    public void setGuide(GuideForSecondLevelCache guide) {
        this.guide = guide;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

}
