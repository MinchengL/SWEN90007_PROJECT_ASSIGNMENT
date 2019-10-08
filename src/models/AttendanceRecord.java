package models;

import java.util.ArrayList;
import java.util.Date;
import data_mapper.AttendanceRecordDataMapper;
import data_mapper.EmployeeDataMapper;
import unitofwork.unitofworkAttendanceRecord;

public class AttendanceRecord {
	
	private int attendancerecord_id = 0;
	private User user=null;
	private String operationType = null;
	private String operationTime = null;
	
	public AttendanceRecord(User user, String operationType, String operationTime) {
		this.user = user;
		this.operationType = operationType;
		this.operationTime = operationTime;
	}
	
	public AttendanceRecord(int id, User user, String operationType, String operationTime) {
		this.attendancerecord_id = id;
		this.user = user;
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

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
		AttendanceRecord record = AttendanceRecordDataMapper.search(this.user.getUserID(), this.getOperationType(), this.getOperationTime());
		if(this.attendancerecord_id == 0) {
			this.attendancerecord_id = record.getattendancerecord_id();
		}
	}

	public static ArrayList<AttendanceRecord> getRecordByEmployee(String id){
		int id_int = Integer.parseInt(id);
		return AttendanceRecordDataMapper.getRecordsByUser(id_int);
	}
	
	public static ArrayList<AttendanceRecord> getAllRecords(){
		return AttendanceRecordDataMapper.getAllRecords();
	}
	
	public static void clockon(String id, String time) {
		Employee employee = Employee.getEmployeeById(id);
		AttendanceRecord record = new AttendanceRecord(employee, "clock on", time);
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
