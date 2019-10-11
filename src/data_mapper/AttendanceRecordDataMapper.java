package data_mapper;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import IdentityMap.EmployeeIdentityMap;
import database.DBConnection;
import models.AttendanceRecord;
import models.Employee;

public class AttendanceRecordDataMapper {
	
	public static AttendanceRecord search(int employee_id, String operation_type, String operation_time){
		String sql = "SELECT id, employee_id, operation_type, operation_time from attendance_record_table WHERE id ="+employee_id+" and operation_type ="+operation_type+" and operation_time="+operation_time;
		Connection connection;
		AttendanceRecord record = null;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int attendancerecord_id = rs.getInt(1);
				int employeeId = rs.getInt(2);
				String operationType = rs.getString(3);
				String operationTime = rs.getString(4);
				Employee employee = Employee.getEmployeeById(employee_id+"");
				record = new AttendanceRecord(attendancerecord_id, employee, operationType, operationTime);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return record;
	}
	
	public static ArrayList<AttendanceRecord> getAllRecords(){
		String sql = "SELECT id, employee_id, operation_type, operation_time from attendance_record_table";
		Connection connection;
		ArrayList<AttendanceRecord> records = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int attendancerecord_id = rs.getInt(1);
				int employee_id = rs.getInt(2);
				String operationType = rs.getString(3);
				String operationTime = rs.getString(4);
				Employee employee = Employee.getEmployeeById(employee_id+"");
				AttendanceRecord record = new AttendanceRecord(attendancerecord_id, employee, operationType, operationTime);
				records.add(record);
				System.out.println(records.size());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return records;
	}
	
	public static ArrayList<AttendanceRecord> getRecordsByUser(int employee_id){
		String sql = "SELECT id, employee_id, operation_type, operation_time from attendance_record_table WHERE employee_id="+employee_id;
		Connection connection;
		ArrayList<AttendanceRecord> records = new ArrayList<>();
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				int attendancerecord_id = rs.getInt(1);
				int employeeId = rs.getInt(2);
				String operationType = rs.getString(3);
				String operationTime = rs.getString(4);
				Employee employee = EmployeeDataMapper.searchbyid(employee_id);
				AttendanceRecord record = new AttendanceRecord(attendancerecord_id, employee, operationType, operationTime);
				records.add(record);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return records;
	}
	
	public static boolean insert(AttendanceRecord record) {
		
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "INSERT INTO attendance_record_table (employee_id, operation_type, operation_time) VALUES ( "
						+record.getEmployee().getUserID()+",'"+record.getOperationType()+"','"+record.getOperationTime()+"')";
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
	
	public static boolean update(AttendanceRecord record) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "UPDATE attendance_record_table SET employee_id ='"+record.getEmployee().getUserID()+"', operation_type= '"+record.getOperationType() 
						+"', operation_time='"+record.getOperationTime()+"' WHERE id ="+ record.getattendancerecord_id();
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

	
	public static boolean delete(AttendanceRecord record) {
		Connection connection;
		int result=0;
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			String sql= "DELETE FROM public.attendance_record_table WHERE id = "+record.getattendancerecord_id();
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
