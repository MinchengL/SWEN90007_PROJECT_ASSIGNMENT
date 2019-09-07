package models;

import java.util.ArrayList;

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
	}
	
	public int getDepartmentID() {
		return departmentID;
	}
	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(Employee employee) {
		this.employees.add(employee);
	}
	public ArrayList<Admin> getAdmins() {
		return admins;
	}
	public void setAdmins(Admin admin) {
		this.admins.add(admin);
	}
	
	
	
	

}
