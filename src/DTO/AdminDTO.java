package DTO;

import java.util.ArrayList;

import com.google.gson.Gson;

import models.Admin;

public class AdminDTO {
	
	private String userName;
	private String passWord;
	private int userID;
	private String firstName;
	private String lastName;
	private ArrayList<DepartmentDTO> department;
	private int phoneNumber;
	private String birthday;
	private String email;
	
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
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
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
	public ArrayList<DepartmentDTO> getDepartment() {
		return department;
	}
	public void setDepartment(ArrayList<DepartmentDTO> department) {
		this.department = department;
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
	
	public static String serialize(AdminDTO adminDTO) {
		Gson gson = new Gson();
		return gson.toJson(adminDTO);
	}
	
	public static AdminDTO deserialize(String dtostr) {
		Gson gson = new Gson();
		return gson.fromJson(dtostr, AdminDTO.class);
	}

}
