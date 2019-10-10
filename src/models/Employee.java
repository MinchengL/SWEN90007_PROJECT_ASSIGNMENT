package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.omg.CORBA.IdentifierHelper;

import IdentityMap.DepartmentIdentityMap;
import IdentityMap.EmployeeIdentityMap;
import dataMapper.DepartmentDataMapper;
import dataMapper.EmployeeDataMapper;
import unitofwork.unitofworkEmployee;

public class Employee extends User{
	
	private String userName = null;
	private String passWord = null;
	private int userID; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	private Department department = null;
	private int phoneNumber;
	private String birthday = null;
	private String email = null;

	public Employee() {}
	
	public Employee(int UserID, String userName, String passWord, String firstName, String lastName,Department department, int phoneNumber, String birthday, String email) {
		
		super(UserID, userName,passWord,firstName,lastName, phoneNumber, birthday, email);
		this.userID = UserID;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = email;
		this.department = department;
	}
	
	public Employee(String userName, String passWord, String firstName, String lastName,Department department, int phoneNumber, String birthday, String email) {
		
		super(userName,passWord,firstName,lastName, phoneNumber, birthday, email);
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = email;
		this.department = department;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerNew(this);
		unitofworkEmployee.getCurrent().commit();
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	
	public String getUserName() {
		return this.userName;
	}
	public String getPassWord() {
		if(this.passWord == null)
		{
			load();
		}
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public int getPhoneNumber() {
		if(this.phoneNumber == 0)
		{
			load();
		}
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public String getBirthday() {
		if(this.birthday == null)
		{
			load();
		}
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public String getEmail() {
		if(this.email == null)
		{
			load();
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public int getUserID() {
		if(this.userID == 0)
		{
			load();
		}
		return userID;
	}
	
	public void setUserID(int userid) {
		this.userID = userid;
		if(unitofworkEmployee.getCurrent()==null) {
			unitofworkEmployee.newCurrent();
		}
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	
	public Department getDepartment() {
		if(this.department==null) {
			load();
		}
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}

	public void load() {
		Employee employee = EmployeeDataMapper.search(this.userName);
		if(this.passWord == null)
		{
			this.passWord = employee.getPassWord();
		}
		if(this.phoneNumber == 0)
		{
			this.phoneNumber = employee.getPhoneNumber();
		}
		if(this.birthday==null) {
			this.birthday = employee.getBirthday();
		}
		if(this.email==null) {
			this.email = employee.getEmail();
		}
		if(this.userID==0) {
			this.userID = employee.getUserID();
		}
		if(this.department==null) {
			this.department = employee.getDepartment();
		}
		
	}

	public static Employee loginbyEmployee(String username, String password) {
		EmployeeIdentityMap employeeIdentityMap = EmployeeIdentityMap.getInstance();
		
		Employee employee = employeeIdentityMap.get(username);
		if(employee!=null) {
			if(employee.getPassWord().equals(password))
			{
				return employee;
			}
			else {
				return null;
			}
		}else {
			employee = EmployeeDataMapper.search(username);
			if(employee!=null) {
				if(employee.getPassWord().equals(password)) {
					employeeIdentityMap.put(username, employee);
					return employee;
				}else {
					return null;
				}
			}
		}
		return null;
	}
	
	public static ArrayList<Employee> getAllEmployeeList(){
		return EmployeeDataMapper.loadAllEmployees();
	}
	
	public static Employee getEmployeeById(String id) {
		int id_int = Integer.parseInt(id);
		Employee employee=EmployeeDataMapper.searchbyid(id_int);
		return employee;
	}
	
	public static Employee getEmployeeByUsername(String username) {
		Employee employee=EmployeeDataMapper.search(username);
		return employee;
	}
}
