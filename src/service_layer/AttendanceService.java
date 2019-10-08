package service_layer;

import java.sql.Date;
import java.util.ArrayList;

import data_mapper.EmployeeDataMapper;
import models.AttendanceRecord;
import models.Employee;

public class AttendanceService {

	public static ArrayList<AttendanceRecord> searchByEmployee(String id) {
		// parse id to int
		int id_int = Integer.parseInt(id);
		ArrayList<AttendanceRecord> list = AttendanceRecord.getRecordByEmployee(id);
		return list;
		
	}
	
	public static ArrayList<AttendanceRecord> getAllAttendance() {
		return AttendanceRecord.getAllRecords();	
	}

	public static void insertClockOnRecord(String id, String time) {
		// TODO Auto-generated method stub
		AttendanceRecord.clockon(id, time);
	}

	public static void insertClockOffRecord(String id, String time) {
		// TODO Auto-generated method stub
		AttendanceRecord.clockoff(id, time);
	}

}
