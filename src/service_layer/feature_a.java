package service_layer;

import database.*;
import models.*;
import unitofwork.unitofworkDepartment;
import data_mapper.*;

import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import IdentityMap.*;
import data_mapper.*;

public class feature_a {
	
	private static final String getUserNumStatement = "SELECT * from user_table";
	
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
	
	public static ArrayList<Department> getAllDepartment(){
		return DepartmentDataMapper.loadAllDepartment();
	}
	
	public static void addDepartment(String name, int phoneNumber, String location) {
		Department department = new Department(name, phoneNumber, location);
		if(unitofworkDepartment.getCurrent()==null) {
			unitofworkDepartment.newCurrent();
		}
		unitofworkDepartment.getCurrent().commit();
		
	}
	
}
