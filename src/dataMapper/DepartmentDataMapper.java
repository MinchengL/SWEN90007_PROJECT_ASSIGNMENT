package dataMapper;

import java.awt.Checkbox;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import IdentityMap.DepartmentIdentityMap;
import database.DBConnection;
import models.Admin;
import models.Department;
import models.Employee;

public class DepartmentDataMapper implements IDepartmentMapper {
	
	public static ArrayList<Department> loadAllDepartment(){
		String sql = "SELECT * from department_table";
		Connection connection = null;
		ArrayList<Department> resList = new ArrayList<>();
		Department result = null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int department_id = rs.getInt(1);
				String name = rs.getString(2);
				String location = rs.getString(3);
				int phoneNumber = rs.getInt(4);
				ArrayList<Admin> admins = AdminDataMapper.loadAdminsbyDepartmentId(department_id);
				ArrayList<Employee> employees = EmployeeDataMapper.searchbydepartment(department_id);
				result = new Department(department_id,name, phoneNumber, location, admins, employees);
				if(DepartmentIdentityMap.getInstance().get(result.getDepartmentID())==null) {
					DepartmentIdentityMap.getInstance().put(department_id, result);
				}
				resList.add(result);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resList;
	}
	
	public static Department search(String parameter, String pvalue){
		String value = pvalue;
		if(parameter.equals("name"))
		{
			value = "'"+pvalue+"'";
		}
		String sql = "SELECT department_id, name, phoneNumber, location from department_table WHERE "+parameter+" = "+value;
		Connection connection = null;
		Department result = null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int department_id = rs.getInt(1);
				String name = rs.getString(2);
				int phoneNumber = rs.getInt(3);
				String location = rs.getString(4);
				ArrayList<Admin> admins = AdminDataMapper.loadAdminsbyDepartmentId(department_id);
				ArrayList<Employee> employees = EmployeeDataMapper.searchbydepartment(department_id);
				result = new Department(department_id,name, phoneNumber, location, null, null);
				if(DepartmentIdentityMap.getInstance().get(result.getDepartmentID())==null) {
					DepartmentIdentityMap.getInstance().put(department_id, result);
				}
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
	
	public static boolean checkDepartmentName(String name) {
		String sql = "SELECT department_id WHERE name = '" + name+"'";
		Connection connection = null;
		boolean result = false;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int department_id = rs.getInt(1);
				result = true;
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
	
	public static boolean insert(Department department) {
		
		Connection connection;
		int result=0;
		int max_id = 0;
		try {
			connection = DBConnection.getConnection();
			Statement id_statement =connection.createStatement();
			String id_sql = "SELECT MAX(department_id) from department_table";
			ResultSet rs = id_statement.executeQuery(id_sql);
			while(rs.next()) {
				max_id = rs.getInt(1);
			}
			int id = max_id+1;
			if(DepartmentIdentityMap.getInstance().get(id)==null) {
				DepartmentIdentityMap.getInstance().put(id, department);
			}
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO department_table (department_id, name, location, phonenumber) VALUES ("+id+",'"+department.getName()+"', '"+department.getLocation()+"', "+department.getPhoneNumber()+")";
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
	
	public static boolean update(Department department) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "UPDATE department_table SET name ='"+department.getName()+ "', location ='"+department.getLocation()+"', phonenumber ="+department.getPhoneNumber()+"WHERE department_id ="+ department.getDepartmentID();
			result = statement.executeUpdate(sql);
			updateAdminList(department);
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
	
	
	
	private static void updateAdminList(Department department) {
		// TODO Auto-generated method stub
		Connection connection;
		int result=0;
		try {
			ArrayList<Admin> admins = new ArrayList<>();
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "SELECT admin_id from admin_department_table WHERE department_id ="+ department.getDepartmentID();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int admin_id = rs.getInt(1);
				for(Admin admin:department.getAdmins()) {
					if(admin.getUserID()!=admin_id) {
						admins.add(admin);
					}
				}
			}
			Statement statement2 = connection.createStatement();
			for(Admin admin: admins) {
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
	
	static ArrayList<Department> loadDepartmentListByAdmin(int admin_id) {
		// TODO Auto-generated method stub
		Connection connection;
		ArrayList<Department> departments = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			Statement statement= connection.createStatement();
			String sql = "SELECT department_id from admin_department_table WHERE admin_id = "+admin_id;
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int department_id = rs.getInt(1);
				Department department = Department.getDepartmentById(department_id+"");
				departments.add(department);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return departments;
	}
	
}
