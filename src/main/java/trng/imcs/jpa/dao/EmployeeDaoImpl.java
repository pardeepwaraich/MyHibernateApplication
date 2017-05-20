package trng.imcs.jpa.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import trng.imcs.jpa.SessionFactoryBuilder;
import trng.imcs.jpa.entities.Employee;

public class EmployeeDaoImpl implements EmployeeDao {

	public boolean addEmployee(Employee emp) {
		Session session = null;
		Transaction transaction = null; 
		try {
			session = openSession();
			transaction = session.beginTransaction();
			session.save(emp);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			if (session.isOpen()) session.close();
		}
		
		return true;
	}

	public boolean deleteEmployee(int empNo) {
		Session session = null;
		Transaction transaction = null; 
		try {
			session = openSession();
			transaction = session.beginTransaction();
			session.delete("Employee", new Integer(empNo));
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			if (session.isOpen()) session.close();
		}
		
		return true;
	}

	public boolean updateEmployee(Employee emp) {
		Session session = null;
		Transaction transaction = null; 
		try {
			session = openSession();
			transaction = session.beginTransaction();
			session.saveOrUpdate(emp);
			session.merge(emp);
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			return false;
		} finally {
			if (session.isOpen()) session.close();
		}
		return true;
	}

	public Employee getEmployee(int empNo) {
		return (Employee) openSession().get(Employee.class, new Integer(empNo));
//		return (Employee) openSession().load(Employee.class, new Integer(empNo));
	}

	private Session openSession() {
		return SessionFactoryBuilder.getSessionFactory().openSession();
	}
}
