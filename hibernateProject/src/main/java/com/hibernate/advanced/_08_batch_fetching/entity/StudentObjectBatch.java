package com.hibernate.advanced._08_batch_fetching.entity;

import javax.persistence.*;

@Entity
@Table(name = "StudentObjectBatch")
public class StudentObjectBatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enrollment_id", nullable = false)
    private String enrollmentId;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "guide_id")
    private GuideObjectBatch guide;

    public StudentObjectBatch() {}

    public StudentObjectBatch(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId;
        this.name = name;
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

    public GuideObjectBatch getGuide() {
        return guide;
    }

    public void setGuide(GuideObjectBatch guide) {
        this.guide = guide;
    }
}
