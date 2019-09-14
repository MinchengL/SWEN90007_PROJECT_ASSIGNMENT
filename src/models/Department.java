package models;

import java.util.ArrayList;
import java.util.HashMap;

import unitofwork.unitofworkDepartment;
import data_mapper.DepartmentDataMapper;

public class Department {
	
	private int departmentID;
	private String name = null;
	private int phoneNumber;
	private String location = null;
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<Admin> admins = new ArrayList<Admin>();
	
	public Department(String name, int phoneNumber, String location) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.location = location;
		unitofworkDepartment.getCurrent().registerNew(this);
	}
	public Department(int departmentID, String name, int phoneNumber, String location, ArrayList<Admin> admins, ArrayList<Employee> employees) {
		this.departmentID=departmentID;
		this.name=name;
		this.phoneNumber=phoneNumber;
		this.location=location;
		this.admins=admins;
		this.employees = employees;
	}
	
	public int getDepartmentID() {
		if(this.departmentID == 0) {
			Department result = DepartmentDataMapper.search("name", this.name);
			this.departmentID =result.getDepartmentID();
		}
		return departmentID;
	}
	public void setDepartmentID(int departmentid) {
		this.departmentID = departmentid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		unitofworkDepartment.getCurrent().registerDirty(this);
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
		unitofworkDepartment.getCurrent().registerDirty(this);
		
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
		unitofworkDepartment.getCurrent().registerDirty(this);
	}
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Employee employee) {
		this.employees.add(employee);
	}
	public ArrayList<Admin> getAdmins() {
		if(admins.size()==0) {
			Department result = DepartmentDataMapper.search("name", this.name);
			this.admins =result.getAdmins();
		}
		return admins;
	}
	public void setAdmins(Admin admin) {
		this.admins.add(admin);
		unitofworkDepartment.getCurrent().registerDirty(this);
	}
}
