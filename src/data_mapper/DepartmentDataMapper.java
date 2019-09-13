package data_mapper;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.Statement;
import java.util.ArrayList;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import com.sun.glass.ui.TouchInputSupport;

import database.DBConnection;
import models.Admin;
import models.Department;

public class DepartmentDataMapper {
	
	public static ArrayList<Department> search(String parameter, String pvalue){
		String value = pvalue;
		if(parameter.equals("name"))
		{
			value = "'"+pvalue+"'";
		}
		String sql = "SELECT department_id, name, phoneNumber, location from department_table WHERE "+parameter+" = "+value;
		Connection connection = null;
		ArrayList<Department> results = new ArrayList<Department>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int department_id = rs.getInt(1);
				String name = rs.getString(2);
				int phoneNumber = rs.getInt(3);
				String location = rs.getString(4);
				Department department = new Department(department_id,name, phoneNumber, location);
				results.add(department);
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
		String sql = "SELECT department_id, name, phoneNumber, location from department_table WHERE "+parameter+" = "+value;
		Connection connection = null;
		String result = null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				if(target.equals("department_id")) {
					int department_id = rs.getInt(1);
					result = department_id+"";
				}else if(target.equals("name")) {
					String name = rs.getString(2);
					result = name;
				}
				else if (target.equals("location")) {
					String location = rs.getString(4);
					result = location;
				}else {
					int phoneNumber = rs.getInt(3);
					result = ""+phoneNumber;
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
	
	public static boolean insert(Department department) {
		
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO department_table (department_id, name, location, phonenumber) VALUES ( "+department.getDepartmentID()+",'"+department.getName()+"', '"+department.getLocation()+"', "+department.getPhoneNumber()+")";
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
	
	public static boolean update(Department department) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "UPDATE department_table SET name ='"+department.getName()+ "', location ='"+department.getName()+"', phonenumber ="+department.getPhoneNumber()+"WHERE department_id ="+ department.getDepartmentID();
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
	
	
	
	public static boolean delete(Department department) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "DELETE FROM public.department_table WHERE department_id = "+department.getDepartmentID();
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
