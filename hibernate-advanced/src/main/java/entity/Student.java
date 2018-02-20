package entity;

import javax.persistence.*;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "enrollmentId", nullable = false)
    private String enrollmentId;

    @Column(name = "name", nullable = false)
    private String name;

    /*
     * Owner of the relationship: Owner is the entity that is persisted to the table that has the foreign key column.
     * 
     * Default fetch type: Eager. It can be changed to fetch = FetchType.LAZY
     * 
     */
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.LAZY)
    @JoinColumn(name = "guide_id")
    private Guide guide;

    public Student() {}

    public Student(String enrollmentId, String name, Guide guide) {
        this.enrollmentId = enrollmentId;
        this.name = name;
        this.guide = guide;
    }

    public Student(String enrollmentId, String name) {
		this.enrollmentId = enrollmentId;
		this.name = name;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Guide getGuide() {
        return guide;
    }

    public void setGuide(Guide guide) {
        this.guide = guide;
    }

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(enrollmentId).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Student)) {
			return false;
		}
		Student other = (Student) obj;
		return new EqualsBuilder().append(enrollmentId, other.enrollmentId).isEquals();
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", enrollmentId=" + enrollmentId + ", name=" + name + ", guide=" + guide + "]";
	}
	
}
