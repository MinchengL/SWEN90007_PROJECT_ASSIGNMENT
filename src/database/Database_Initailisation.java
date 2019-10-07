package database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;

public class Database_Initailisation {

	public static String ADMIN_TABLE_CHECK = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'admin_table'";
	public static String EMPLOYEE_TABLE_CHECK = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'employee_table'";
	public static String DEPARTMENT_TABLE_CHECK = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'department_table'";
	public static String ADMIN_DEPARTMENT_TABLE_CHECK = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'admin_department_table'";
	public static String ATTENDANCE_RECORD_TABLE_CHECK = "SELECT COUNT(*) FROM information_schema.tables WHERE table_name = 'attendance_record_table'";
	
	public static String ADMIN_TABLE_CREATE = "CREATE TABLE admin_table (admin_id serial, username VARCHAR(25),password VARCHAR(25), firstname VARCHAR(25), lastname VARCHAR(25), phoneNumber INT, birthday VARCHAR(25), email VARCHAR(25), PRIMARY KEY (admin_id));";
	public static String EMPLOYEE_TABLE_CREATE = "CREATE TABLE employee_table (employee_id serial, username VARCHAR(25),password VARCHAR(25), firstname VARCHAR(25), lastname VARCHAR(25), phoneNumber INT, birthday VARCHAR(25), email VARCHAR(25), department_id INT REFERENCES department_table(department_id), PRIMARY KEY (employee_id));";
	public static String DEPARTMENT_TABLE_CREATE = "CREATE TABLE department_table (department_id serial, name VARCHAR(25), location VARCHAR(25), phoneNumber INT, PRIMARY KEY (department_id));";
	public static String ADMIN_DEPARTMENT_TABLE_CREATE = "CREATE TABLE admin_department_table (id serial, admin_id INT REFERENCES admin_table(admin_id), department_id INT REFERENCES department_table(department_id), PRIMARY KEY (id));";
	public static String ATTENDANCE_RECORD_TABLE_CREATE = "CREATE TABLE attendance_record_table(id serial,  employee_id INT REFERENCES employee_table(employee_id), operation_type VARCHAR(25), operation_time VARCHAR(25), PRIMARY KEY(id));";
	
	public static void initailize_dataset() {
		try {
			Connection connection = DBConnection.getConnection();
			Statement admin_check_statement = connection.createStatement();
			ResultSet rs1 = admin_check_statement.executeQuery(ADMIN_TABLE_CHECK);
			if(rs1.next()) {
				int count = rs1.getInt(1);
				if(count == 0) {
					Statement admin_statement = connection.createStatement(); 
					admin_statement.execute(ADMIN_TABLE_CREATE);
					System.out.println("created");
				}
			}
			Statement department_check_statement = connection.createStatement();
			ResultSet rs2  = department_check_statement.executeQuery(DEPARTMENT_TABLE_CHECK);
			if(rs2.next()) {
				int count = rs2.getInt(1);
				if(count == 0) {
					Statement department_statement = connection.createStatement();
					department_statement.execute(DEPARTMENT_TABLE_CREATE);
					System.out.println("created");
				}
			}
			Statement employee_check_statement = connection.createStatement();
			ResultSet rs3 = employee_check_statement.executeQuery(EMPLOYEE_TABLE_CHECK);
			if(rs3.next()) {
				int count = rs3.getInt(1);
				if(count == 0) {
					Statement employee_statement = connection.createStatement(); 
					employee_statement.execute(EMPLOYEE_TABLE_CREATE);
					System.out.println("created");
				}
				
			}
			Statement admin_department_check_statement = connection.createStatement();
			ResultSet rs4 = admin_department_check_statement.executeQuery(ADMIN_DEPARTMENT_TABLE_CHECK);
			if(rs4.next())  {
				int count = rs4.getInt(1);
				if(count == 0) {
					Statement admin_department_statement = connection.createStatement();
					admin_department_statement.execute(ADMIN_DEPARTMENT_TABLE_CREATE);
					System.out.println("created");
				}
			}
			
			Statement attendance_record_check_statement = connection.createStatement();
			ResultSet rs5 = attendance_record_check_statement.executeQuery(ATTENDANCE_RECORD_TABLE_CHECK);
			if(rs5.next())  {
				int count = rs5.getInt(1);
				if(count == 0) {
					Statement attendance_record_statement = connection.createStatement();
					attendance_record_statement.execute(ATTENDANCE_RECORD_TABLE_CREATE);
					System.out.println("created");
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	

}
