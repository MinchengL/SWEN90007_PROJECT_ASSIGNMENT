package models;

import java.util.ArrayList;
import java.util.Date;

public class Admin extends User{
	
	private String userName = null;
	private String passWord = null;
	private int userID; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	private ArrayList<Department> department = new ArrayList<Department>();
	private int phoneNumber;
	private Date birthday = null;
	private String email = null;

	public Admin(String userName, String firstName, String lastName) {
		super(userName, firstName, lastName);
		// TODO Auto-generated constructor stub
	}

}
