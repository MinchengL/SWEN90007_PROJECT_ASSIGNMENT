package IdentityMap;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class AttendanceRecordIdentityMap {
	
	private static AttendanceRecordIdentityMap attendanceRecordIdentityMap = null;
	private static Map<Integer, AttendanceRecord> map = new HashMap<>();

	private AttendanceRecordIdentityMap() {}
	
	public static AttendanceRecordIdentityMap getInstance(){
		if(attendanceRecordIdentityMap == null) {
			attendanceRecordIdentityMap = new AttendanceRecordIdentityMap();
		}
		return attendanceRecordIdentityMap;
	}
	
	public void put(int id, AttendanceRecord record) {
		map.put(id, record);
	}
	
	public AttendanceRecord get(int id) {
		return map.get(id);
	}
}
