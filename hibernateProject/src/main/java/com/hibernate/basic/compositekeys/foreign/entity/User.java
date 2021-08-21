package com.hibernate.basic.compositekeys.foreign.entity;

import javax.persistence.*;
import java.util.StringJoiner;

@Entity
public class User {

    @EmbeddedId
    private UserId userId;

    @Column(nullable = false)
    private String email;

    /*
        Derived Identifier Mapping

        We are borrowing department id, so it can be part of a composite primary key
        By using mapsid we avoid the duplication of the department id.

        Users table                                                                Department table
        -----------                                                                ----------------
        email, department_id_cpk_col2, username_cpk_col1, department_id_fk ----->  id, name

        We will have:

        Users table                                           Department table
        -----------                                           ----------------
        email, username_cpk_col1, department_id_fk -------->  id, name

    */
    @ManyToOne
    @JoinColumn(name = "department_id_fk")
    @MapsId("departmentId") // this ignores the departmentId value of the UserId
    protected Department department;

    public User() {}

    public User(UserId userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public UserId getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("email='" + email + "'")
                .add("department=" + department)
                .toString();
    }

}
