package trng.imcs.jpa.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "Emp")
public class Employee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long empNo;
	@Column(name="empname")
	private String name;
	private Float salary;
	private java.sql.Date joinDate;
	
	
	
	public Employee(Long empNo, String name, Float salary, Date joinDate) {
		super();
		this.empNo = empNo;
		this.name = name;
		this.salary = salary;
		this.joinDate = joinDate;
	}
	public Long getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Long empNo) {
		this.empNo = empNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Float getSalary() {
		return salary;
	}
	public void setSalary(Float salary) {
		this.salary = salary;
	}
	
	public java.sql.Date getJoinDate() {
		return joinDate;
	}
	@Temporal(TemporalType.DATE)
	public void setJoinDate(java.sql.Date joinDate) {
		this.joinDate = joinDate;
	} 
	
	
}
