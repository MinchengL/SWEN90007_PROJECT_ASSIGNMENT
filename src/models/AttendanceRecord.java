package models;

import java.util.ArrayList;
import java.util.Date;
import data_mapper.AttendanceRecordDataMapper;
import data_mapper.EmployeeDataMapper;
import unitofwork.unitofworkAttendanceRecord;

public class AttendanceRecord {
	
	private int attendancerecord_id = 0;
	private Employee employee=null;
	private String operationType = null;
	private String operationTime = null;
	
	public AttendanceRecord() {}
	
	public AttendanceRecord(Employee employee, String operationType, String operationTime) {
		this.employee = employee;
		this.operationType = operationType;
		this.operationTime = operationTime;
	}
	
	public AttendanceRecord(int id, Employee employee, String operationType, String operationTime) {
		this.attendancerecord_id = id;
		this.employee = employee;
		this.operationTime = operationTime;
		this.operationType = operationType;
	}
	
	public int getattendancerecord_id() {
		if(this.attendancerecord_id == 0) {
			load();
		}
		return attendancerecord_id;
	}
	
	public void setattendancerecord_id(int id) {
		this.attendancerecord_id = id;
		if(unitofworkAttendanceRecord.getCurrent()==null) {
			unitofworkAttendanceRecord.newCurrent();
		}
		unitofworkAttendanceRecord.getCurrent().registerDirty(this);
	}

	public Employee getEmployee() {
		return employee;
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
		if(unitofworkAttendanceRecord.getCurrent()==null) {
			unitofworkAttendanceRecord.newCurrent();
		}
		unitofworkAttendanceRecord.getCurrent().registerDirty(this);
	}

	public String getOperationType() {
		return operationType;
	}
	
	public void setOperationType(String type) {
		this.operationType = type;
		if(unitofworkAttendanceRecord.getCurrent()==null) {
			unitofworkAttendanceRecord.newCurrent();
		}
		unitofworkAttendanceRecord.getCurrent().registerDirty(this);
	}

	public String getOperationTime() {
		return operationTime;
	}
	
	public void setOpeartionTime(String time) {
		this.operationTime = time;
		if(unitofworkAttendanceRecord.getCurrent()==null) {
			unitofworkAttendanceRecord.newCurrent();
		}
		unitofworkAttendanceRecord.getCurrent().registerDirty(this);
	}
	
	private void load() {
		// TODO Auto-generated method stub
		AttendanceRecord record = AttendanceRecordDataMapper.search(this.employee.getUserID(), this.getOperationType(), this.getOperationTime());
		if(this.attendancerecord_id == 0) {
			this.attendancerecord_id = record.getattendancerecord_id();
		}
	}

	public static ArrayList<AttendanceRecord> getRecordByEmployee(String id){
		int id_int = Integer.parseInt(id);
		return AttendanceRecordDataMapper.getRecordsByUser(id_int);
	}
	
	public static ArrayList<AttendanceRecord> getAllRecords(){
		ArrayList<AttendanceRecord> list = AttendanceRecordDataMapper.getAllRecords();
		System.out.println(list.size());
		return list;
	}
	
	public static void clockon(String id, String time) {
		Employee employee = Employee.getEmployeeById(id);
		AttendanceRecord record = new AttendanceRecord(employee, "clock on", time);
		System.out.println(time.length());
		System.out.println(time);
		if(unitofworkAttendanceRecord.getCurrent()==null) {
			unitofworkAttendanceRecord.newCurrent();
		}
		unitofworkAttendanceRecord.getCurrent().registerNew(record);
		unitofworkAttendanceRecord.getCurrent().commit();
	}
	
	public static void clockoff(String id, String time) {
		Employee employee = Employee.getEmployeeById(id);
		AttendanceRecord record = new AttendanceRecord(employee, "clock off", time);
		if(unitofworkAttendanceRecord.getCurrent()==null) {
			unitofworkAttendanceRecord.newCurrent();
		}
		unitofworkAttendanceRecord.getCurrent().registerNew(record);
		unitofworkAttendanceRecord.getCurrent().commit();
	}
	
}
