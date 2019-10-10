package models;

import java.util.ArrayList;
import java.util.HashMap;

import IdentityMap.DepartmentIdentityMap;
import unitofwork.unitofworkDepartment;
import dataMapper.DepartmentDataMapper;

public class Department {
	
	private int departmentID;
	private String name = null;
	private int phoneNumber;
	private String location = null;
	private ArrayList<Employee> employees = new ArrayList<Employee>();
	private ArrayList<Admin> admins = new ArrayList<Admin>();
	
	public Department() {}
	
	public Department(String name, int phoneNumber, String location) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.location = location;
		//System.out.println(unitofworkDepartment.getCurrent() == null);
		if(unitofworkDepartment.getCurrent()==null) {
			unitofworkDepartment.newCurrent();
		}
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
			load();
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
		if(unitofworkDepartment.getCurrent()==null) {
			unitofworkDepartment.newCurrent();
		}
		unitofworkDepartment.getCurrent().registerDirty(this);
	}
	public ArrayList<Employee> getEmployees() {
		if(employees.size()==0) {
			load();
		}
		return employees;
	}
	public ArrayList<Admin> getAdmins() {
		if(admins.size()==0) {
			load();
		}
		return admins;
	}
	public void setAdmins(Admin admin) {
		this.admins.add(admin);
		unitofworkDepartment.getCurrent().registerDirty(this);
	}
	private void load() {
		// TODO Auto-generated method stub
		Department result = DepartmentDataMapper.search("name", this.name);
		if(this.departmentID==0) {
			this.departmentID = result.getDepartmentID();
		}
		if(this.employees.size()==0) {
			this.employees = result.getEmployees();
		}
		if(this.admins.size()==0) {
			this.admins = result.getAdmins();
		}
	}
	
	public static ArrayList<Department> getAllDepartmentList(){
		return DepartmentDataMapper.loadAllDepartment();
	}
	
	public static Department getDepartmentById(String id) {
		int id_int = Integer.parseInt(id);
		Department department = DepartmentIdentityMap.getInstance().get(id_int);
		if(department==null) {
			department = DepartmentDataMapper.search("department_id", id);
		}
		return department;
	}
	
	public static Department getDepartmentByName(String name) {
		Department department = DepartmentDataMapper.search("name", name);
		return department;
	}
	
}
