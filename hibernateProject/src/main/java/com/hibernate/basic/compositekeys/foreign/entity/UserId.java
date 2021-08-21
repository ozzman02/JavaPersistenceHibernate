package com.hibernate.basic.compositekeys.foreign.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.StringJoiner;

@Embeddable
public class UserId implements Serializable {

    @Column(name="username_cpk_col1")
    private String username;

    @Column(name="department_id_cpk_col2")
    private Long departmentId;

    protected UserId() {
    }

    public UserId(String username, Long departmentId) {
        this.username = username;
        this.departmentId = departmentId;
    }

    public String getUsername() {
        return username;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UserId)) {
            return false;
        }
        UserId that = (UserId) obj;
        return new EqualsBuilder().append(this.username, that.username)
                .append(this.departmentId, that.departmentId)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(username)
                .append(departmentId)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserId.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("departmentId=" + departmentId)
                .toString();
    }

}
