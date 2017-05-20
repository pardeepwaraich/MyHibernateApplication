package trng.imcs.jpa.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import trng.imcs.jpa.SessionFactoryBuilder;
import trng.imcs.jpa.entities.Course;
import trng.imcs.jpa.entities.ReportBean;
import trng.imcs.jpa.entities.SearchCriteria;
import trng.imcs.jpa.entities.Student;
import trng.imcs.jpa.entities.StudentAddress;
import trng.imcs.jpa.util.ReportResultTransformer;

public class StudentDao implements IStudentDao {
	SessionFactory sf;
	final static Logger logger = Logger.getLogger(StudentDao.class);
	
	public StudentDao() {
		sf = SessionFactoryBuilder.getSessionFactory();
	}

	public void addStudent(Student student) throws CustomException {
		logger.debug("Executing StudentDao::addStudent API" + student.getId());
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        Transaction transaction = session.beginTransaction();
        
        try {
			session.save(student);
		} catch (Exception e) {
			logger.error("failed to execute addStudent method", e);
		}
		transaction.commit();
        session.close();
        logger.debug("Completed executing StudentDao::addStudent API");
	}

	public Student loadStudent(Long stdId) throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        Student student = (Student) session.get(Student.class, stdId);
        
        session.getTransaction().commit();
        session.close();
        
        return student;
	}

	public void updateStudent(Student student) throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        session.update(student);
        
        
        session.getTransaction().commit();
        session.close();
	}

	public void deleteStudent(Long stdId) throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        Student student = (Student) session.load(Student.class, stdId);
        session.delete(student);
        
        session.getTransaction().commit();
        session.close();
	}

	public List<Student> getStudentDetails() throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        
        Query query =  session.createQuery("FROM Student");
        List<Student> students = query.list();
        
        session.close();
        
        return students;
	}

	/**
	 * Returns address for the given studentId
	 */
	public StudentAddress getStudentAddress(Long studentId) throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        Query query =  session.createQuery("FROM StudentAddress where id=:studentId");
        query.setParameter("studentId", studentId);
        List<StudentAddress> studentAddressLst = query.list();
//        return (StudentAddress) query.uniqueResult();
        
        session.getTransaction().commit();
        session.close();
        
        if (studentAddressLst != null && studentAddressLst.size() > 0) {
        	return studentAddressLst.get(0);
        }
        
		return null;
	}

	public List<Student> getStudentDetailsWithJoinFetch() throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();
        
        Query query =  session.createQuery("SELECT s FROM Student as s inner join s.studentAddress join fetch s.marksDetails");
        query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        List<Student> studentsLst = query.list();
        
        session.getTransaction().commit();
        session.close();
        
       	return studentsLst;
	}
	
	public List<Student> getStudentDetailsWithCriteria(SearchCriteria searchCriteria) throws CustomException {
    	Session session = sf.openSession();
        String studentAlias = "s";
        String studentAddressPath = studentAlias + ".studentAddress";
        String studentMarksPath = studentAlias + ".marksDetails";
        
        session.beginTransaction();
		Criteria studentCritiera = session.createCriteria(Student.class, studentAlias)
					.setFetchMode(studentAddressPath, FetchMode.JOIN)
					.setFetchMode(studentMarksPath, FetchMode.EAGER)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		
//		Criteria addressCriteria = studentCritiera.createCriteria(studentAlias);
		
        Criteria addressCriteria = studentCritiera.createCriteria(studentAddressPath);
        Criteria marksDetailsCriteria = studentCritiera.createCriteria(studentMarksPath);
        
        if (searchCriteria.getStudentId() != null) {
        	studentCritiera.add(Restrictions.eq("id", searchCriteria.getStudentId()));
        }
        
        if (searchCriteria.getAddress() != null) {
        	addressCriteria.add(Restrictions.eq("address", searchCriteria.getAddress()));
        }
        
        if (searchCriteria.getResult() != null) {
        	marksDetailsCriteria.add(Restrictions.eq("result", searchCriteria.getResult()));
        	marksDetailsCriteria.addOrder(Order.asc("result"));
        }
        
        List<Student> studentsLst = studentCritiera.list();
        
        session.getTransaction().commit();
        session.close();
        
       	return studentsLst;
	}
	

	public List<Student> getStudentByAddress(String address) throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        
        Query query =  session.getNamedQuery("findStudentByAddress");
//        query.setFirstResult(5);
//        query.setMaxResults(50);
        query.setParameter("studentAddress", address);
        List<Student> studentsLst = query.list();
        
        session.close();
        
       	return studentsLst;
	}

	public void addCourse(Course course) throws CustomException {
    	sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        session.beginTransaction();

        session.save(course);
        
        session.getTransaction().commit();
	}
	
	public List<ReportBean> queryReportData() {
		sf = SessionFactoryBuilder.getSessionFactory();
        Session session = sf.openSession();
        
        Query query =  session.createQuery("select s.id, m.maxMarks, ad.address from Student as s inner join s.studentAddress as ad inner join s.marksDetails as m");
        query.setResultTransformer(new ReportResultTransformer());
        List<ReportBean> reportBeans = query.list();

        session.close();
        
        return reportBeans;
	}
}
