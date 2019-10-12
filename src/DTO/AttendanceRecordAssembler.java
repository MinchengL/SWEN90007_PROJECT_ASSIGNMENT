package DTO;

import java.util.ArrayList;

import dataMapper.AttendanceRecordDataMapper;
import dataMapper.DepartmentDataMapper;
import models.Admin;
import models.AttendanceRecord;
import models.Department;
import models.Employee;

public class AttendanceRecordAssembler {
	
	public static AttendanceRecordDTO createAttendanceRecordDTO(AttendanceRecord attendanceRecord) {
		// TODO Auto-generated method stub
		AttendanceRecordDTO dto = new AttendanceRecordDTO();
		dto.setAttendancerecord_id(attendanceRecord.getattendancerecord_id());
		dto.setOperationTime(attendanceRecord.getOperationTime());
		dto.setOperationType(attendanceRecord.getOperationType());
		EmployeeDTO employeeDTO = EmployeeAssembler.createEmployeeDTO(attendanceRecord.getEmployee());
		dto.setUser(employeeDTO);
		return dto;
	}

	public static void updateAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
		
		AttendanceRecord record = new AttendanceRecord();
		record.setattendancerecord_id(attendanceRecordDTO.getAttendancerecord_id());
		record.setOpeartionTime(attendanceRecordDTO.getOperationTime());
		record.setOperationType(attendanceRecordDTO.getOperationType());
		Employee employee = EmployeeAssembler.createEmployee(attendanceRecordDTO.getUser());
		record.setEmployee(employee);
		
		AttendanceRecordDataMapper.update(record);
		
	}
	
	public static AttendanceRecord createAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
		
		AttendanceRecord record = new AttendanceRecord();
		record.setattendancerecord_id(attendanceRecordDTO.getAttendancerecord_id());
		record.setOpeartionTime(attendanceRecordDTO.getOperationTime());
		record.setOperationType(attendanceRecordDTO.getOperationType());
		Employee employee = EmployeeAssembler.createEmployee(attendanceRecordDTO.getUser());
		record.setEmployee(employee);
		
		return record;
		
	}
	
}
