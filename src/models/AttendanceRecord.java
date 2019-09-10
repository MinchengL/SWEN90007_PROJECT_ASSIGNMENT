package models;

import java.util.Date;

import javax.print.attribute.standard.DateTimeAtCompleted;

public class AttendanceRecord {
	
	private User user=null;
	private String operationType = null;
	private Date operationTime = null;
	
	public AttendanceRecord(User user, String operationType) {
		this.user = user;
		this.operationType = operationType;
		this.operationTime = new Date();
	}

	public User getUser() {
		return user;
	}

	public String getOperationType() {
		return operationType;
	}

	public Date getOperationTime() {
		return operationTime;
	}
	

}
