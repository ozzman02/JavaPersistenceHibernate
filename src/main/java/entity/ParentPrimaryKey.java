package entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
public class ParentPrimaryKey implements Serializable {

    private String firstname;

    private String lastname;

    public ParentPrimaryKey() {}

    public ParentPrimaryKey(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public int hashCode() {
        int result = firstname.hashCode();
        result = 31 * result + lastname.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        ParentPrimaryKey parentPrimaryKey = (ParentPrimaryKey) obj;

        if (!firstname.equals(parentPrimaryKey.firstname)) {
            return false;
        }

        if (!lastname.equals(parentPrimaryKey.lastname)) {
            return false;
        }

        return true;
    }
}
