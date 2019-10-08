package models;

import java.util.ArrayList;

import IdentityMap.AdminIdentityMap;
import data_mapper.AdminDataMapper;
import unitofwork.unitofworkAdmin;

public class Admin extends User{
	
	private String userName = null;
	private String passWord;
	private int userID = 0; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	private ArrayList<Department> department = new ArrayList<Department>();
	private int phoneNumber = 0;
	private String birthday = null;
	private String email = null;

	public Admin(String userName, String passWord, String firstName, String lastName, int phoneNumber, String birthday, String email) {
		super(userName, passWord, firstName, lastName, phoneNumber, birthday, email);
		unitofworkAdmin.getCurrent().registerNew(this);
	}
	public Admin(int UserID, String userName, String passWord, String firstName, String lastName, int phoneNumber, String birthday, String email, ArrayList<Department> department) {
		super(UserID, userName, passWord,firstName, lastName, phoneNumber, birthday, email);
		this.passWord = passWord;
		this.department = department;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
		unitofworkAdmin.getCurrent().registerDirty(this);
	}
	public String getPassWord() {
		if(this.passWord == "")
		{
			load();
		}
		return this.passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
		unitofworkAdmin.getCurrent().registerDirty(this);
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
		unitofworkAdmin.getCurrent().registerDirty(this);
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
		unitofworkAdmin.getCurrent().registerDirty(this);
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
		unitofworkAdmin.getCurrent().registerDirty(this);
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
		unitofworkAdmin.getCurrent().registerDirty(this);
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
		unitofworkAdmin.getCurrent().registerDirty(this);
	}
	public int getUserID() {
		if(userID == 0)
		{
			load();
		}
		return userID;
	}
	
	public ArrayList<Department> getDepartment() {
		if(department.size()==0) {
			load();
		}
		return department;
	}

	public void setDepartment(ArrayList<Department> department) {
		this.department = department;
		unitofworkAdmin.getCurrent().registerDirty(this);
	}

	public void load() {
		Admin admin = AdminDataMapper.search("username", this.userName);
		if(passWord == null)
		{
			this.passWord =admin.getPassWord();
		}
		if(phoneNumber == 0)
		{
			this.phoneNumber =admin.getPhoneNumber();
		}
		if(birthday==null) {
			this.birthday = admin.getBirthday();
		}
		if(email==null) {
			this.email = admin.getEmail();
		}
		if(userID==0) {
			this.userID = admin.getUserID();
		}
		if(department.size()==0) {
			this.department = admin.getDepartment();
		}
	}
	
	public static Admin loginbyAdmin(String username, String password) {
		AdminIdentityMap adminIdentityMap = AdminIdentityMap.getInstance();
		
		Admin admin = adminIdentityMap.get(username);
		if(admin != null) {
			if(admin.getPassWord().equals(password)) {
				return admin;
			}else {
				return null;
			}
		}else{
			admin = AdminDataMapper.search("username", username);
			if(admin!=null) {
				if(admin.getPassWord().equals(password)) {
					adminIdentityMap.put(username, admin);
					return admin;
				}else {
					return null;
				}
			}
		}
		return null;
	}
	

}
