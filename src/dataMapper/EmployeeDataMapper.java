package dataMapper;

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

import IdentityMap.DepartmentIdentityMap;
import IdentityMap.EmployeeIdentityMap;
import database.DBConnection;

public class EmployeeDataMapper {
	
	public static Employee search(String name){
		String sql = "SELECT employee_id, username, password, firstname, lastname, department_id, phoneNumber, birthday, email from employee_table WHERE username = '"+name+"'";
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
				Department department = Department.getDepartmentById(department_id+"");
				employee = new Employee(employee_id, username, password, firstname, lastname, department, phoneNumber, birthday, email);
				if(EmployeeIdentityMap.getInstance().get(employee_id)==null) {
					EmployeeIdentityMap.getInstance().put(employee_id, employee);
				}
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
	
	public static Employee searchbyid(int id){
		String sql = "SELECT employee_id, username, password, firstname, lastname, department_id, phoneNumber, birthday, email from employee_table WHERE employee_id = "+id;
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
				Department department = Department.getDepartmentById(department_id+"");
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
	
	public static ArrayList<Employee> searchbydepartment(int departmentid){
		String sql = "SELECT employee_id from employee_table WHERE department_id = "+departmentid;
		Connection connection;
		ArrayList<Employee> employees = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			Employee employee;
			while(rs.next()) {
				int employee_id = rs.getInt(1);
				employee=EmployeeIdentityMap.getInstance().get(employee_id);
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}
	
	public static ArrayList<Employee> loadAllEmployees(){
		String sql = "SELECT employee_id, username, password, firstname, lastname, department_id, phoneNumber, birthday, email from employee_table";
		Connection connection;
		ArrayList<Employee> employees = new ArrayList<>();
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
				Department department = Department.getDepartmentById(department_id+"");
				Employee employee = new Employee(employee_id, username, password, firstname, lastname, department, phoneNumber, birthday, email);
				if(EmployeeIdentityMap.getInstance().get(employee_id)==null) {
					EmployeeIdentityMap.getInstance().put(employee_id, employee);
				}
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return employees;
	}
	
	public static int getEmployeeMaxId() {
		Connection connection;
		int max_id=0;
		try {
			connection = DBConnection.getConnection();
			Statement id_statement =connection.createStatement();
			String id_sql = "SELECT MAX(employee_id) from employee_table";
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
	
	public static boolean insert(Employee employee) {
		
		Connection connection;
		int result=0;
		int max_id=0;
		try {
			connection = DBConnection.getConnection();
			int max_employee_id = getEmployeeMaxId();
			int max_admin_id = AdminDataMapper.getAdminMaxId();
			max_id = Math.max(max_employee_id, max_admin_id);
			int id = max_id+1;
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO employee_table (employee_id, username, password, firstname, lastname, department_id, phoneNumber, birthday, email) VALUES ( "
						+id+",'"+employee.getUserName()+"','"+employee.getPassWord()+"','"+employee.getFirstName()+"', '"+employee.getLastName()+"',"+employee.getDepartment().getDepartmentID()
						+","+employee.getPhoneNumber()+",'"+employee.getBirthday()+"','"+employee.getEmail()+"')";
			result = statement.executeUpdate(sql);
			if(EmployeeIdentityMap.getInstance().get(id)==null) {
				EmployeeIdentityMap.getInstance().put(id, employee);
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
	
	
	
	public static boolean delete(Employee employee) {
		Connection connection;
		int result=0;
		try {
			ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<>();
			attendanceRecords=AttendanceRecord.getRecordByEmployee(employee.getUserID()+"");
			if(attendanceRecords.size()>0) {
				for(int i=0; i<attendanceRecords.size(); i++) {
					AttendanceRecordDataMapper.delete(attendanceRecords.get(i));
				}
			}
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

