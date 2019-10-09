package DTO;

import java.util.ArrayList;

import com.google.gson.Gson;

import models.Admin;
import models.Employee;

public class DepartmentDTO {
	private int departmentID;
	private String name;
	private int phoneNumber;
	private String location;
	private ArrayList<EmployeeDTO> employees;
	private ArrayList<AdminDTO> admins;
	
	public static String serialize(DepartmentDTO departmentDTO) {
		Gson gson = new Gson();
		return gson.toJson(departmentDTO);
	}
	
	public static DepartmentDTO deserialize(String dtostr) {
		Gson gson = new Gson();
		return gson.fromJson(dtostr, DepartmentDTO.class);
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public ArrayList<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(ArrayList<EmployeeDTO> employees) {
		this.employees = employees;
	}

	public ArrayList<AdminDTO> getAdmins() {
		return admins;
	}

	public void setAdmins(ArrayList<AdminDTO> admins) {
		this.admins = admins;
	}
}
