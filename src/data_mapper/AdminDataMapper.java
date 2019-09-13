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

public class AdminDataMapper {
	
	public static ArrayList<Admin> searchbyid(int id){
		String sql = "SELECT admin_id, username,firstname, lastname from admin_table WHERE admin_id ="+id;
		Connection connection;
		ArrayList<Admin> results = new ArrayList<Admin>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int admin_id = rs.getInt(1);
				String username = rs.getString(2);
				String firstname = rs.getString(3);
				String lastname = rs.getString(4);
				Admin admin = new Admin(username, firstname, lastname);
				results.add(admin);
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
		String sql = "SELECT admin_id, phonenumber, birthday, email, password from admin_table WHERE "+parameter+" = "+value;
		Connection connection = null;
		HashMap<String, String> result = new HashMap<>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				result.put("admin_id", rs.getInt(1)+"");
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
	
	public static boolean insert(Admin admin) {
		
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO admin_table (username, firstname, lastname) VALUES ( "+admin.getUserName()+",'"+admin.getFirstName()+"', '"+admin.getLastName()+")";
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
	
	public static boolean update(Admin admin) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "UPDATE admin_table SET username ='"+admin.getUserName()+ "', firstname ='"+admin.getFirstName()+"', phonenumber ="+admin.getLastName()+"WHERE admin_id ="+ admin.getUserID();
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
	
	
	
	public static boolean delete(Admin admin) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "DELETE FROM public.admin_table WHERE admin_id = "+admin.getUserID();
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

