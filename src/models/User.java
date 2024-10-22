package models;

import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import IdentityMap.AdminIdentityMap;
import IdentityMap.EmployeeIdentityMap;
import models.Department;

public abstract class User {
	
	private String userName = null;
	private String passWord = null;
	private int userID =0; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	//private ArrayList<Department> department = new ArrayList<Department>();
	private int phoneNumber;
	private String birthday = null;
	private String email = null;
	
	public User() {}
	
	public User(int userID, String userName, String password, String firstName, String lastName, int phoneNumber, String birthday, String email)
	{
		//userID should be generated automatically here.
		//There could be different rewrite methods with different parameters.
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.passWord = password;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = email;
		
	}
	
	public User(String userName, String passWord, String firstName, String lastName, int phoneNumber, String birthday, String email) {
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = email;
	}
	
	public static User getUser(String id) {
		int id_int = Integer.parseInt(id);
		Admin admin = Admin.getAdminById(id_int);
		if(admin != null) return (Admin)admin;
		Employee employee = Employee.getEmployeeById(id);
		if(employee != null) return (Employee)employee;
		return null;
	}
	
	public static User getUserByUsername(String username) {
		Admin admin = Admin.getAdminbyUsername(username);
		if(admin != null) return admin;
		Employee employee = Employee.getEmployeeByUsername(username);
		if(employee != null) return employee;
		return null;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getUserID() {
		return userID;
	}
	
	
	
	
}
