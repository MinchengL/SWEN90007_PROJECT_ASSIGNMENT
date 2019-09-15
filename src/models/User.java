package models;

import java.net.URISyntaxException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import models.Department;

public abstract class User {
	
	private String userName = null;
	private String passWord = null;
	private int userID; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	//private ArrayList<Department> department = new ArrayList<Department>();
	private int phoneNumber;
	private String birthday = null;
	private String email = null;
	
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
		this.firstName = firstName;
		this.lastName = lastName;
		this.passWord = passWord;
		this.phoneNumber = phoneNumber;
		this.birthday = birthday;
		this.email = email;
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
