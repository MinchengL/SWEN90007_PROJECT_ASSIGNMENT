package models;

import java.util.ArrayList;
import java.util.Date;

public class Employee extends User{
	
	private String userName = null;
	private String passWord = null;
	private int userID; // corresponds to id in database
	private String firstName = null;
	private String lastName = null;
	private ArrayList<Department> department = new ArrayList<Department>();
	private int phoneNumber;
	private Date birthday = null;
	private String email = null;

	public Employee(String userName, String passWord, String firstName, String lastName,
			ArrayList<Department> department) {
		super(userName, passWord, firstName, lastName, department);
		// TODO Auto-generated constructor stub
	}

}
