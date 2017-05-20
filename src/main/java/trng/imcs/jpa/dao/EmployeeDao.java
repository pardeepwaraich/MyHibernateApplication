package trng.imcs.jpa.dao;

import trng.imcs.jpa.entities.Employee;

public interface EmployeeDao {

    public boolean addEmployee(Employee emp);

    public boolean deleteEmployee(int empNo);

    public boolean updateEmployee(Employee emp);

    public Employee getEmployee(int empNo);

}
