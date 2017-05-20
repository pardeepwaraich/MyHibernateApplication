package trng.imcs.jpa.dao;

import java.util.List;

import trng.imcs.jpa.entities.Course;
import trng.imcs.jpa.entities.ReportBean;
import trng.imcs.jpa.entities.SearchCriteria;
import trng.imcs.jpa.entities.Student;
import trng.imcs.jpa.entities.StudentAddress;

public interface IStudentDao {
	public void addStudent(Student std) throws CustomException;
	public Student loadStudent(Long stdId) throws CustomException;
	public void updateStudent(Student std) throws CustomException;
	public void deleteStudent(Long stdId) throws CustomException;
	public List<Student> getStudentDetails() throws CustomException;
	public StudentAddress getStudentAddress(Long studentId) throws CustomException;
	public List<Student> getStudentDetailsWithJoinFetch() throws CustomException;
	public List<Student> getStudentDetailsWithCriteria(SearchCriteria searchCriteria) throws CustomException;
	public List<Student> getStudentByAddress(String address) throws CustomException;
	public void addCourse(Course course) throws CustomException;
	public List<ReportBean> queryReportData();
}