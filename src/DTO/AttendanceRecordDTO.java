package DTO;

import com.google.gson.Gson;

import models.User;

public class AttendanceRecordDTO {
	
	private int attendancerecord_id;
	private EmployeeDTO user;
	private String operationType;
	private String operationTime;
	
	public int getAttendancerecord_id() {
		return attendancerecord_id;
	}
	public void setAttendancerecord_id(int attendancerecord_id) {
		this.attendancerecord_id = attendancerecord_id;
	}
	public EmployeeDTO getUser() {
		return user;
	}
	public void setUser(EmployeeDTO employeeDTO) {
		this.user = employeeDTO;
	}
	public String getOperationType() {
		return operationType;
	}
	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public String getOperationTime() {
		return operationTime;
	}
	public void setOperationTime(String operationTime) {
		this.operationTime = operationTime;
	}

	public static String serialize(AttendanceRecordDTO attendanceRecordDTO) {
		Gson gson = new Gson();
		return gson.toJson(attendanceRecordDTO);
	}
	
	public static AttendanceRecordDTO deserialize(String dtostr) {
		Gson gson = new Gson();
		return gson.fromJson(dtostr, AttendanceRecordDTO.class);
	}
	
}
