package unitofwork;

import java.util.ArrayList;

import models.AttendanceRecord;
import models.AttendanceRecord;
import dataMapper.*;

public class unitofworkAttendanceRecord {
	
	private static ThreadLocal<unitofworkAttendanceRecord> current = new ThreadLocal<>();
	
	private ArrayList<AttendanceRecord> newAttendanceRecords = new ArrayList<AttendanceRecord>();
	private ArrayList<AttendanceRecord> dirtyAttendanceRecords = new ArrayList<AttendanceRecord>();
	private ArrayList<AttendanceRecord> deletedAttendanceRecords = new ArrayList<AttendanceRecord>();
	
	public static void setCurrent(unitofworkAttendanceRecord uow) {
		current.set(uow);
	}
	
	public static void newCurrent() {
		setCurrent(new unitofworkAttendanceRecord());
	}
	
	public static unitofworkAttendanceRecord getCurrent() {
		return (unitofworkAttendanceRecord) current.get();
	}
	
	public void registerNew(AttendanceRecord attendanceRecord) {
		assert (attendanceRecord.getattendancerecord_id()<=0):"id is null";
		assert (!dirtyAttendanceRecords.contains(attendanceRecord)):"this object is dirty";
		assert (!deletedAttendanceRecords.contains(attendanceRecord)):"this object has been deleted";
		assert (!newAttendanceRecords.contains(attendanceRecord)):"this object is new";
		newAttendanceRecords.add(attendanceRecord);
	}
	
	public void registerDirty(AttendanceRecord attendanceRecord) {
		assert (attendanceRecord.getattendancerecord_id()<=0):"id is null";
		assert (!deletedAttendanceRecords.contains(attendanceRecord)):"this object has been deleted";
		if(!dirtyAttendanceRecords.contains(attendanceRecord) && !newAttendanceRecords.contains(attendanceRecord)) {
			dirtyAttendanceRecords.add(attendanceRecord);
		}
	}
	
	public void registerDeleted(AttendanceRecord attendanceRecord) {
		assert (attendanceRecord.getattendancerecord_id()<=0):"id is null";
		if(newAttendanceRecords.remove(attendanceRecord)) return;
		dirtyAttendanceRecords.remove(attendanceRecord);
		if(!deletedAttendanceRecords.contains(attendanceRecord)) {
			deletedAttendanceRecords.add(attendanceRecord);
		}
	}
	
	public void commit() {
		for(AttendanceRecord attendanceRecord: newAttendanceRecords) {
			AttendanceRecordDataMapper.insert(attendanceRecord);
		}
	
		for(AttendanceRecord attendanceRecord: dirtyAttendanceRecords) {
			AttendanceRecordDataMapper.update(attendanceRecord);
		}
		
		for(AttendanceRecord attendanceRecord: deletedAttendanceRecords) {
			AttendanceRecordDataMapper.delete(attendanceRecord);
		}
		
	}
}
