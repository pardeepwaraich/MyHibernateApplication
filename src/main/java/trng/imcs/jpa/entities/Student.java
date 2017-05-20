package trng.imcs.jpa.entities;

/**
 *
 */
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "student")
@NamedQuery(name = "findStudentByAddress", query = "select DISTINCT(std) from Student as std inner join std.studentAddress stdAddress where stdAddress.address like :studentAddress")
@NamedNativeQueries({
	@NamedNativeQuery(
	name = "ClassFirstStudent",
	query = "CALL ClassFirstStudent(:class)",
	resultClass = Student.class
	)
})
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Default is AUTO, 
	@Column(name = "student_id")
	private long id;

	@Column(name = "first_name", nullable=false)
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
	//mappedBy attribute is only necessary for a bidirectional relationship, 
	private StudentAddress studentAddress;
	
	@OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<MarksDetails> marksDetails;
	
	@ManyToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL}) //(targetEntity = Course.class) target entity class does not need to be specified when we use Collection with type as follows.
//	@JoinTable(name="Student_Courses", joinColumns={@JoinColumn(name="Student_Id")}, inverseJoinColumns={@JoinColumn(name="courseId")})
	private Set<Course> selectedCourses; 
	
	public Student(){
		
	}

	public Student(String firstName, String lastName, String email, String phone) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public StudentAddress getStudentAddress() {
		return studentAddress;
	}

	public void setStudentAddress(StudentAddress studentAddress) {
		this.studentAddress = studentAddress;
	}
	
	public Set<MarksDetails> getMarksDetails() {
		return marksDetails;
	}

	public void setMarksDetails(Set<MarksDetails> marksDetails) {
		this.marksDetails = marksDetails;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", firstName=" + firstName + ", lastName="
				+ lastName + ", email=" + email + ", phone=" + phone + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((marksDetails == null) ? 0 : marksDetails.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((studentAddress == null) ? 0 : studentAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (marksDetails == null) {
			if (other.marksDetails != null)
				return false;
		} else if (!marksDetails.equals(other.marksDetails))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (studentAddress == null) {
			if (other.studentAddress != null)
				return false;
		} else if (!studentAddress.equals(other.studentAddress))
			return false;
		return true;
	}

	public Set<Course> getSelectedCourses() {
		return selectedCourses;
	}

	public void setSelectedCourses(Set<Course> selectedCourses) {
		this.selectedCourses = selectedCourses;
	}

	
	
}
