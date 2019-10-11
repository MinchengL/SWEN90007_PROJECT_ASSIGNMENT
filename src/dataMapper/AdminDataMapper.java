package dataMapper;

import models.*;
import java.awt.List;
import java.awt.event.ItemEvent;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import IdentityMap.AdminIdentityMap;
import database.DBConnection;

public class AdminDataMapper {
	
	public static Admin search(String parameter, String pvalue){
		String value = pvalue;
		if(parameter.equals("username"))
		{
			value = "'"+pvalue+"'";
		}
		String sql = "SELECT admin_id, username, password, firstname, lastname, phoneNumber, birthday, email from admin_table WHERE "+parameter+" = "+value;
		Connection connection;
		Admin admin = null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int admin_id = rs.getInt(1);
				String username = rs.getString(2);
				String password = rs.getString(3);
				String firstname = rs.getString(4);
				String lastname = rs.getString(5);
				int phoneNumber = rs.getInt(6);
				String birthday = rs.getString(7);
				String email = rs.getString(8);
				ArrayList<Department> departments = DepartmentDataMapper.loadDepartmentListByAdmin(admin_id);
				admin = new Admin(admin_id,username,password, firstname, lastname,phoneNumber, birthday, email, departments);
				if(AdminIdentityMap.getInstance().get(admin_id)==null) {
					AdminIdentityMap.getInstance().put(admin_id, admin);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return admin;
	}

	public static int getAdminMaxId() {
		Connection connection;
		int max_id=0;
		try {
			connection = DBConnection.getConnection();
			Statement id_statement =connection.createStatement();
			String id_sql = "SELECT MAX(admin_id) from admin_table";
			ResultSet rs = id_statement.executeQuery(id_sql);
			while(rs.next()) {
				max_id = rs.getInt(1);
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				DBConnection.connection.rollback();
			}catch (SQLException ignored) {
				// TODO: handle exception
				System.out.println("rollback failed");
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return max_id;
		
	}
	
	public static boolean insert(Admin admin) {
		
		Connection connection;
		int max_id=0;
		int result=0;
		try {
			if(AdminIdentityMap.getInstance().get(admin.getUserID())==null) {
				AdminIdentityMap.getInstance().put(admin.getUserID(), admin);
			}
			connection = DBConnection.getConnection();
			int max_employee_id = EmployeeDataMapper.getEmployeeMaxId();
			int max_admin_id = AdminDataMapper.getAdminMaxId();
			max_id = Math.max(max_employee_id, max_admin_id);
			int id = max_id+1;
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO admin_table (admin_id, username, password, firstname, lastname, phoneNumber, birthday, email) VALUES ( "
						+id+",'"+admin.getUserName()+"','"+admin.getPassWord()+"','"+admin.getFirstName()+"','"+admin.getLastName()
						+"',"+admin.getPhoneNumber()+",'"+admin.getBirthday()+"','"+admin.getEmail()+"')";
			result = statement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				DBConnection.connection.rollback();
			}catch (SQLException ignored) {
				// TODO: handle exception
				System.out.println("rollback failed");
			}
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
			String sql= "UPDATE admin_table SET username ='"+admin.getUserName()+"', password= '"+admin.getPassWord() +"', firstname ='"
					+admin.getFirstName()+"', lastname='"+admin.getLastName()+ "', phonenumber ="+admin.getPhoneNumber()+", birthday = '"
					+admin.getBirthday()+"', email='"+admin.getEmail()+"' WHERE admin_id ="+ admin.getUserID();
			result = statement.executeUpdate(sql);
			updateAdminDepartmentList(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				DBConnection.connection.rollback();
			}catch (SQLException ignored) {
				// TODO: handle exception
				System.out.println("rollback failed");
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result>0?true:false;
	}
	
	
	
	private static void updateAdminDepartmentList(Admin admin) {
		// TODO Auto-generated method stub
		Connection connection;
		int result=0;
		try {
			ArrayList<Department> departments = new ArrayList<>();
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "SELECT from department_id from admin_department_table WHERE admin_id ="+ admin.getUserID();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int department_id = rs.getInt(1);
				for(Department department:admin.getDepartment()) {
					if(department.getDepartmentID()!=department_id) {
						departments.add(department);
					}
				}
			}
			Statement statement2 = connection.createStatement();
			for(Department department:departments) {
				String sql2= "INSERT INTO admin_department_table(admin_id, department_id) VALUES ("+admin.getUserID()+","+department.getDepartmentID()+")";
				result = statement2.executeUpdate(sql2);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				DBConnection.connection.rollback();
			}catch (SQLException ignored) {
				// TODO: handle exception
				System.out.println("rollback failed");
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	static ArrayList<Admin> loadAdminsbyDepartmentId(int department_id) {
		// TODO Auto-generated method stub
		Connection connection;
		ArrayList<Admin> admins = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			Statement statement= connection.createStatement();
			String sql = "SELECT admin_id from admin_department_table WHERE department_id = "+department_id;
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int admin_id = rs.getInt(1);
				Admin admin = AdminIdentityMap.getInstance().get(admin_id);
				admins.add(admin);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return admins;
	}
	
}

