package trng.imcs.jpa.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity
@Table(name = "marks_details")
public class MarksDetails {

	@Id
	@GeneratedValue
	@Column(name = "test_id")
	private long testId;

	@Column(name = "subject")
	private String subject;

	@Column(name = "max_marks")
	private String maxMarks;

	@Column(name = "marks_obtained")
	private String marksObtained;

	@Column(name = "result")
	private String result;

	@Formula("(marks_obtained / max_marks)*100")
    private float percentage;
	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable=false)
	private Student student;

	
	public MarksDetails() {
	}

	public MarksDetails(String subject, String maxMarks, String marksObtained,
			String result) {
		this.subject = subject;
		this.maxMarks = maxMarks;
		this.marksObtained = marksObtained;
		this.result = result;
	}

	public long getTestId() {
		return testId;
	}

	public void setTestId(long testId) {
		this.testId = testId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMaxMarks() {
		return maxMarks;
	}

	public void setMaxMarks(String maxMarks) {
		this.maxMarks = maxMarks;
	}

	public String getMarksObtained() {
		return marksObtained;
	}

	public void setMarksObtained(String marksObtained) {
		this.marksObtained = marksObtained;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "MarksDetails [testId=" + testId + ", subject=" + subject + ", maxMarks=" + maxMarks + ", marksObtained="
				+ marksObtained + ", result=" + result + ", percentage=" + percentage + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marksObtained == null) ? 0 : marksObtained.hashCode());
		result = prime * result + ((maxMarks == null) ? 0 : maxMarks.hashCode());
		result = prime * result + Float.floatToIntBits(percentage);
		result = prime * result + ((this.result == null) ? 0 : this.result.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		result = prime * result + (int) (testId ^ (testId >>> 32));
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
		MarksDetails other = (MarksDetails) obj;
		if (marksObtained == null) {
			if (other.marksObtained != null)
				return false;
		} else if (!marksObtained.equals(other.marksObtained))
			return false;
		if (maxMarks == null) {
			if (other.maxMarks != null)
				return false;
		} else if (!maxMarks.equals(other.maxMarks))
			return false;
		if (Float.floatToIntBits(percentage) != Float.floatToIntBits(other.percentage))
			return false;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		if (testId != other.testId)
			return false;
		return true;
	}
	
	
}
