package entity;

import javax.persistence.*;

@Entity
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    /*
        This is important to define the Foreign Keys
     */
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "firstname_fk", referencedColumnName = "firstname"),
            @JoinColumn(name = "lastname_fk", referencedColumnName = "lastname")
    })
    private Parent parent;

    public Child() {
    }

    public Child(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

}
