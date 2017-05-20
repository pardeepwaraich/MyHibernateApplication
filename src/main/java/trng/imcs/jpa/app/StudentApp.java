package trng.imcs.jpa.app;

/**
 *
 */
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.apache.log4j.Logger;

import trng.imcs.jpa.dao.CustomException;
import trng.imcs.jpa.dao.IStudentDao;
import trng.imcs.jpa.dao.StudentDao;
import trng.imcs.jpa.entities.Course;
import trng.imcs.jpa.entities.MarksDetails;
import trng.imcs.jpa.entities.SearchCriteria;
import trng.imcs.jpa.entities.Student;
import trng.imcs.jpa.entities.StudentAddress;


public class StudentApp
{
	final static Logger logger = Logger.getLogger(StudentApp.class);

	IStudentDao studentDao;
	static Scanner scanner;
	
	public StudentApp() {
		studentDao = new StudentDao();
		scanner = new Scanner(System.in);
	}
	
    public static void main( String[] args ) throws CustomException {
    	StudentApp stdApp = new StudentApp();
    	
    	System.out.println("Choose the operation");
    	boolean flag = true;

//    	stdApp.insertCourses();
    	
    	do {
        	System.out.println("1 Add \n 2 DELETE \n 3 UPDATE \n 4 SELECT \n 5. Add Student with Details \n "
        			+ "6. List Students \n 7. Select Address \n 8. Serach on Criteria \n 9. Select Student by Address \n 10. Query Report ");
        	StudentOperation opVal = StudentOperation.getValue(scanner.nextInt());

	    	switch(opVal) {
	    		case ADD_STUDENT:
	    			stdApp.addStudent(); break;
	    			
	    		case DELETE_STUDENT:
	    			stdApp.deleteStudent(); break;
	
	    		case UPDATE_STUDENT:
	    			stdApp.updateStudent(); break;
	    	
	    		case SELECT_STUDENT:
	    			Student std = stdApp.selectStudent(); 
	    			System.out.println(std);
	    			break;
	    			
	    		case ADD_STUDENT_WITH_DETAILS:
	    			stdApp.addStudentWithDetail(); 
	    			break;
	
	    		case LIST_STUDENTS:
	    			stdApp.listStudents(); 
	    			break;

	    		case SELECT_ADDRESS:
	    			stdApp.selectAddress(); 
	    			break;
	
	    		case SEARCH_ON_CRITERIA:
	    			stdApp.searchOnCriteria(); 
	    			break;
	    		
	    		case SELECT_STUDENT_BY_ADDRESS:
	    			stdApp.selectStudentByAddress(); 
	    			break;

	    		case QUERY_REPORT:
	    			stdApp.queryReport(); 
	    			break;

	    		default: flag = false;
	    	}
    	} while (flag);
    	
    	scanner.close();
    }
    
    
    private void queryReport() {
    	System.out.println(studentDao.queryReportData());
	}

	private void insertCourses() throws CustomException {
    	try {
			Course course1 = new Course(1l, "Java", 100);
			studentDao.addCourse(course1);
			
			Course course2 = new Course(2l, "SAP", 150);
			studentDao.addCourse(course2);
			
			
			Course course3 = new Course(3l, "C++", 50);
			studentDao.addCourse(course3);
			
			
			Course course4 = new Course(4l, ".Net", 100);
			studentDao.addCourse(course4);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void selectStudentByAddress() throws CustomException {
    	System.out.println("Enter Address: ");
    	String stdAddress = scanner.nextLine();

    	List<Student> students = studentDao.getStudentByAddress(stdAddress);
    	System.out.println(students);
	}

	private void searchOnCriteria() throws CustomException {
//    	System.out.println("Enter student ID: ");
    	Long stdId = 1l;

//    	System.out.println("Enter Address: ");
//    	String address = "ABC Street";

//    	System.out.println("Enter Result: ");
//    	String result  = "Dist";

    	SearchCriteria sc = new SearchCriteria();
    	sc.setStudentId(stdId);
//    	sc.setResult(result);
//    	sc.setAddress(address);
    	
    	List<Student> students = studentDao.getStudentDetailsWithCriteria(sc);
    	for (Student student : students) {
			System.out.println(student);
			System.out.println(student.getStudentAddress());
			System.out.println(student.getMarksDetails());
		}
	}

	private void selectAddress() throws CustomException {
    	System.out.println("Enter student ID: ");
    	Long stdId = scanner.nextLong();

    	StudentAddress studentAddress = studentDao.getStudentAddress(stdId);
    	System.out.println(studentAddress);
	}

	private void listStudents() throws CustomException {
//    	List<Student> students = studentDao.getStudentDetails();
		List<Student> students = studentDao.getStudentDetailsWithJoinFetch();
    	for (Student student : students) {
			System.out.println(student);
			System.out.println(student.getStudentAddress());
			System.out.println(student.getMarksDetails());
		}
	}

	private Student selectStudent() throws CustomException {
    	System.out.println("Enter student ID: ");
    	Long stdId = scanner.nextLong();
    	return studentDao.loadStudent(stdId);
	}

	private void deleteStudent() throws CustomException {
		System.out.println("Enter student ID: ");
		Long stdId = scanner.nextLong();
    	studentDao.deleteStudent(stdId);
	}

	private void addStudent() throws CustomException {
        Student student = new Student("John", "Pingel", "jpingel@gmail.com", "547595623" );

        studentDao.addStudent(student);
    }

	
	private void addStudentWithDetail() throws CustomException {
        Student student = new Student("John", "Pingel", "jpingel@gmail.com", "547595623" );
        StudentAddress studentAddress = new StudentAddress("ABC Street", "Blr", "KA", "India");
        
        MarksDetails mathMarks = new MarksDetails("Maths", "100", "90", "Dist");
        MarksDetails scienceMarks = new MarksDetails("Science", "100", "60", "First");
        mathMarks.setStudent(student);
        scienceMarks.setStudent(student);
        
        Set<MarksDetails> marksDetails = new HashSet<MarksDetails>(); 
        
        
        marksDetails.add(mathMarks);
        marksDetails.add(scienceMarks);
        
        student.setMarksDetails(marksDetails);
        student.setStudentAddress(studentAddress);
        studentAddress.setStudent(student);
        
        Course course1 = new Course(null, "Java", 100);
		Course course2 = new Course(null, "SAP", 150);
		Set<Course> selectedCourses = new HashSet<Course>();
		selectedCourses.add(course1);
		selectedCourses.add(course2);
		
		student.setSelectedCourses(selectedCourses);
		
        studentDao.addStudent(student);
    }
    

    private void updateStudent() throws CustomException {
        System.out.println("Enter student ID: ");
        Long stdId = scanner.nextLong();
        
        Student student = new Student("David", "Robert", "drobert@gmail.com", "547595623" );
        student.setId(stdId);
        studentDao.updateStudent(student);
    }
    
    
    enum StudentOperation {
		ADD_STUDENT(1), DELETE_STUDENT(2), UPDATE_STUDENT(3), SELECT_STUDENT(4), ADD_STUDENT_WITH_DETAILS(5), 
		LIST_STUDENTS(6), SELECT_ADDRESS(7), SEARCH_ON_CRITERIA(8), SELECT_STUDENT_BY_ADDRESS (9), QUERY_REPORT (10);
		
		int operationVal;
		
		StudentOperation(int opVal) {
			operationVal = opVal;
		}
		
		static StudentOperation getValue(int opVal) {
			for (StudentOperation studentOperation : StudentOperation.values()) {
				if (opVal == studentOperation.operationVal) {
					return studentOperation;
				}
			}
			
			return null;
		}
	}
}
