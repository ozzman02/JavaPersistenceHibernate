package com.hibernate.basic._06_mapping_enums.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name="employee_id", unique=true)
    private String employeeId;

    //@Enumerated
    @Enumerated(EnumType.STRING)
    //@Enumerated(EnumType.ORDINAL)
    @Column(name="employee_status")
    private EmployeeStatus employeeStatus;

    public Employee() {}

    public Employee(String name, String employeeId, EmployeeStatus employeeStatus) {
        this.name = name;
        this.employeeId = employeeId;
        this.employeeStatus = employeeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("employeeId", employeeId)
                .append("employeeStatus", employeeStatus)
                .toString();
    }
}
