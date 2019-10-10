package RemoteFacade;

import DTO.AttendanceRecordAssembler;
import DTO.AttendanceRecordDTO;
import dataMapper.AttendanceRecordDataMapper;
import models.AttendanceRecord;

public class AttendanceRecordFacade {

	public AttendanceRecordDTO getAttendanceRecord(int attendanceRecord_id, String operation_time, String operation_type) {
		AttendanceRecord attendanceRecord = AttendanceRecordDataMapper.search(attendanceRecord_id, operation_type, operation_time);
		AttendanceRecordDTO dto = AttendanceRecordAssembler.createAttendanceRecordDTO(attendanceRecord);
		return dto;
	}
	
	public void updateAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
		AttendanceRecordAssembler.updateAttendanceRecord(attendanceRecordDTO);
	}
	
	public String getAttendanceRecordJson(int attendanceRecord_id, String operation_time, String operation_type) {
		AttendanceRecord attendanceRecord = AttendanceRecordDataMapper.search(attendanceRecord_id, operation_type, operation_time);
		AttendanceRecordDTO dto = AttendanceRecordAssembler.createAttendanceRecordDTO(attendanceRecord);
		return AttendanceRecordDTO.serialize(dto);
	}
	
	public void updateAttendanceRecordJson(String json) {
		AttendanceRecordDTO attendanceRecordDTO = AttendanceRecordDTO.deserialize(json);
		AttendanceRecordAssembler.updateAttendanceRecord(attendanceRecordDTO);
	}
	
}
