package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.omg.CORBA.IdentifierHelper;

import IdentityMap.DepartmentIdentityMap;
import data_mapper.DepartmentDataMapper;
import data_mapper.EmployeeDataMapper;
import unitofwork.unitofworkEmployee;

public class Employee extends User{
	
	private String userName = null;
	private String passWord = null;
	private int userID; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	private Department department = null;
	private int phoneNumber=0;
	private String birthday = null;
	private String email = null;

	public Employee(int UserID, String userName, String passWord, String firstName, String lastName,Department department, int phoneNumber, String birthday, String email) {
		
		super(UserID, userName,passWord,firstName,lastName, phoneNumber, birthday, email);
		this.department = department;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public String getPassWord() {
		if(passWord == null)
		{
			load();
		}
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public int getPhoneNumber() {
		if(phoneNumber == 0)
		{
			load();
		}
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public String getBirthday() {
		if(birthday == null)
		{
			load();
		}
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public String getEmail() {
		if(email == null)
		{
			load();
		}
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
		unitofworkEmployee.getCurrent().registerDirty(this);
	}
	public int getUserID() {
		if(userID == 0)
		{
			load();
		}
		return userID;
	}
	
	public Department getDepartment() {
		if(department==null) {
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
		if(passWord == null)
		{
			this.passWord = employee.getPassWord();
		}
		if(phoneNumber == 0)
		{
			this.phoneNumber = employee.getPhoneNumber();
		}
		if(birthday==null) {
			this.birthday = map.get("birthday");
		}
		if(email==null) {
			this.email = map.get("email");
		}
		if(userID==0) {
			this.userID = Integer.parseInt(map.get("admin_id"));
		}
		if(department==null) {
			int department_id = Integer.parseInt(map.get("department_id"));
			this.department = DepartmentIdentityMap.getInstance().get(department_id);
			if(this.department==null) {
				HashMap<String, String> result= DepartmentDataMapper.search("name", "IT");
    				department = new Department(Integer.parseInt(result.get("department_id")), result.get("name"), Integer.parseInt(result.get("phonenumber")),result.get("location"));
    				DepartmentIdentityMap.getInstance().put(department.getDepartmentID(), department);
			}
		}
		
	}

}
