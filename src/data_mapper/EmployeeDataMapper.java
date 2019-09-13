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

	public static HashMap<String, String> searchfordetails(String parameter, String pvalue) {
		String value = pvalue;
		if(parameter.equals("name"))
		{
			value = "'"+pvalue+"'";
		}
		String sql = "SELECT employee_id, phonenumber, birthday, email, password from employee_table WHERE "+parameter+" = "+value;
		Connection connection = null;
		HashMap<String, String> result = new HashMap<>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				result.put("employee_id", rs.getInt(1)+"");
				result.put("phonenumber",rs.getInt(2)+"");
				result.put("birthday", rs.getString(3));
				result.put("email", rs.getString(4));
				result.put("password", rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

