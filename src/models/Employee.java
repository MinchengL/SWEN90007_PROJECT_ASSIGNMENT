package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import data_mapper.EmployeeDataMapper;
import unitofwork.unitofworkEmployee;

public class Employee extends User{
	
	private String userName = null;
	private String passWord = null;
	private int userID; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	private ArrayList<Department> department = new ArrayList<Department>();
	private int phoneNumber;
	private String birthday = null;
	private String email = null;

	public Employee(String userName, String firstName, String lastName) {
		super(userName, firstName, lastName);
		// TODO Auto-generated constructor stub
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
	
	public void load() {
		HashMap<String, String> map = new HashMap<>();
		map = EmployeeDataMapper.searchfordetails("username", this.userName);
		if(passWord == null)
		{
			this.passWord = map.get("password");
		}
		if(phoneNumber == 0)
		{
			this.phoneNumber = Integer.parseInt(map.get("phonenumber"));
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
		
	}

}
