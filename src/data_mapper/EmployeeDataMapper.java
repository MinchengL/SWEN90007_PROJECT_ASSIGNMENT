package data_mapper;

import models.*;
import java.awt.List;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;
import javax.print.attribute.ResolutionSyntax;

import database.DBConnection;

public class EmployeeDataMapper {
	
	public static Employee search(String name){
		String sql = "SELECT employee_id, username, password, firstname, lastname, department_id, phoneNumber, birthday, email from employee_table WHERE name = '"+name+"'";
		Connection connection;
		Employee employee=null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int employee_id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstname = rs.getString(4);
				String lastname = rs.getString(5);
				int department_id = rs.getInt(6);
				int phoneNumber = rs.getInt(7);
				String birthday = rs.getString(8);
				String email = rs.getString(9);
				Department department = loadDepartment(department_id);
				employee = new Employee(employee_id, username, password, firstname, lastname, department, phoneNumber, birthday, email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employee;
	}
	
	public static boolean insert(Employee employee) {
		
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO employee_table (employee_id, username, password, firstname, lastname, department_id, phoneNumber, birthday, email from employee_table) VALUES ( "
						+employee.getUserID()+",'"+employee.getUserName()+"','"+employee.getPassWord()+"','"+employee.getFirstName()+"', '"+employee.getLastName()+"',"+employee.getDepartment().getDepartmentID()
						+","+employee.getPhoneNumber()+"','"+employee.getBirthday()+"','"+employee.getEmail()+"')";
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result>0?true:false;
		
	}
	
	public static boolean update(Employee employee) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "UPDATE employee_table SET username ='"+employee.getUserName()+"', password= '"+employee.getPassWord() +"', firstname ='"
						+employee.getFirstName()+"', lastname='"+employee.getLastName()+ "', department_id = "+ employee.getDepartment().getDepartmentID()
						+", phonenumber ="+employee.getPhoneNumber()+", birthday = '"+employee.getBirthday()+"', email='"+employee.getEmail()
						+"' WHERE employee_id ="+ employee.getUserID();
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result>0?true:false;
	}
	
	
	
	public static boolean delete(Employee employee) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "DELETE FROM public.employee_table WHERE employee_id = "+employee.getUserID();
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result>0?true:false;
	}
	
}
