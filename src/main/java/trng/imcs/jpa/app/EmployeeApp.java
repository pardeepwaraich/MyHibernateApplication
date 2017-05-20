package trng.imcs.jpa.app;

import java.util.Date;
import java.util.Scanner;

import trng.imcs.jpa.dao.EmployeeDao;
import trng.imcs.jpa.dao.EmployeeDaoImpl;
import trng.imcs.jpa.entities.Employee;

public class EmployeeApp 
{
	EmployeeDao employeeDao;  
	static Scanner scanner;
	
	public EmployeeApp() 
	{
		employeeDao = new EmployeeDaoImpl();
		scanner = new Scanner(System.in);
	}
	
    public static void main( String[] args ) 
    {
    	EmployeeDao employeeDao = new EmployeeDaoImpl();
    	
    	employeeDao.addEmployee(new Employee(101l, "Raj", 5000.00f, new java.sql.Date(new java.util.Date().getTime())));
    }
    
}
