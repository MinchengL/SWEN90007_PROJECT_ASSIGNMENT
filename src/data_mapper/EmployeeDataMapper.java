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

import database.DBConnection;

public class EmployeeDataMapper {
	
	public static ArrayList<Employee> searchbyid(int id){
		String sql = "SELECT employee_id, username,firstname, lastname from employee_table WHERE employee_id ="+id;
		Connection connection;
		ArrayList<Employee> results = new ArrayList<Employee>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int employee_id = rs.getInt(1);
				String username = rs.getString(2);
				String firstname = rs.getString(3);
				String lastname = rs.getString(4);
				Employee employee = new Employee(username, firstname, lastname);
				results.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(results.size());
		return results;
	}

	public static String searchfordetails(String parameter, String pvalue, String target) {
		if(parameter.equals(target)) {
			System.out.println("The search target shoudl not be the input.");
		}
		String value = pvalue;
		if(parameter.equals("name"))
		{
			value = "'"+pvalue+"'";
		}
		String sql = "SELECT employee_id, phonenumber, birthday, email from employee_table WHERE "+parameter+" = "+value;
		Connection connection = null;
		String result = null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				if(target.equals("employee_id")) {
					int employee_id = rs.getInt(1);
					result = employee_id+"";
				}else if(target.equals("phonenumber")) {
					int phonenumber = rs.getInt(2);
					result = phonenumber+"";
				}
				else if (target.equals("birthday")) {
					String birthday = rs.getString(4);
					result = birthday;
				}else {
					String email = rs.getString(3);
					result = email;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		return result;
	}
	
	public static boolean insert(Employee employee) {
		
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO employee_table (username, firstname, lastname) VALUES ( "+employee.getUserName()+",'"+employee.getFirstName()+"', '"+employee.getLastName()+")";
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
			String sql= "UPDATE employee_table SET username ='"+employee.getUserName()+ "', firstname ='"+employee.getFirstName()+"', phonenumber ="+employee.getLastName()+"WHERE employee_id ="+ employee.getUserID();
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

