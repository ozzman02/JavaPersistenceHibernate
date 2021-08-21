package com.hibernate.basic.compositekeys.primary.entity;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

@Entity
public class Parent {

    @EmbeddedId
    private ParentPrimaryKey parentPrimaryKey;

    @OneToMany(mappedBy="parent", cascade={CascadeType.PERSIST})
    private Set<Child> children = new HashSet<>();

    public Parent() { }

    public Parent(ParentPrimaryKey parentPrimaryKey) {
        this.parentPrimaryKey = parentPrimaryKey;
    }

    public void addChild(Child child) {
        child.setParent(this);
        this.children.add(child);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Parent.class.getSimpleName() + "[", "]")
                .add("parentPrimaryKey=" + parentPrimaryKey)
                .add("children=" + children)
                .toString();
    }
}
