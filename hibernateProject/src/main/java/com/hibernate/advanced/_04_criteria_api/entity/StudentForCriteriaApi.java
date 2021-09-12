package com.hibernate.advanced._04_criteria_api.entity;

import com.hibernate.advanced._03_jpql.entity.Student3;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class StudentForCriteriaApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="enrollment_id", nullable=false)
    private String enrollmentId;

    private String name;

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name="guide_id")
    private GuideForCriteriaApi guide;

    public StudentForCriteriaApi() {}

    public StudentForCriteriaApi(String enrollmentId, String name) {
        this.enrollmentId = enrollmentId;
        this.name = name;
    }

    public StudentForCriteriaApi(String enrollmentId, String name, GuideForCriteriaApi guide) {
        this.enrollmentId = enrollmentId;
        this.name = name;
        this.guide = guide;
    }

    public GuideForCriteriaApi getGuide() {
        return guide;
    }

    public void setGuide(GuideForCriteriaApi guide) {
        this.guide = guide;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Student3.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("enrollmentId='" + enrollmentId + "'")
                .add("name='" + name + "'")
                .add("guide='" + guide + "'")
                .toString();
    }

}
