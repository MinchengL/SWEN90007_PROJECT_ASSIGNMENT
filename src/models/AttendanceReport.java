package models;

import java.util.ArrayList;
import java.util.Date;

public class AttendanceReport {
	
	private User user;
	private Date startTime;
	private Date endTime;
	
	public AttendanceReport(User user, Date startTime,Date endTime) {
		this.user=user;
		this.startTime=startTime;
		this.endTime=endTime;
	}
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public User getUser() {
		return user;
	}
	
	public ArrayList<AttendanceRecord> getAttendanceRecords(){
		return null;
		
	}
	
	
}
