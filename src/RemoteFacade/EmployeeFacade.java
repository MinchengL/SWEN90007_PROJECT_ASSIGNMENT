package RemoteFacade;

import DTO.EmployeeAssembler;
import DTO.EmployeeDTO;
import data_mapper.EmployeeDataMapper;
import models.Employee;

public class EmployeeFacade {
	
	public EmployeeDTO getEmployee(int employee_id) {
		Employee employee = EmployeeDataMapper.searchbyid(employee_id);
		EmployeeDTO dto = EmployeeAssembler.createEmployeeDTO(employee);
		return dto;
	}
	
	public void updateEmployee(EmployeeDTO employeeDTO) {
		EmployeeAssembler.updateEmployee(employeeDTO);
	}
	
	public String getEmployeeJson(int employee_id) {
		Employee employee = EmployeeDataMapper.searchbyid(employee_id);
		EmployeeDTO dto = EmployeeAssembler.createEmployeeDTO(employee);
		return EmployeeDTO.serialize(dto);
	}
	
	public void updateEmployeeJson(String json) {
		EmployeeDTO employeeDTO = EmployeeDTO.deserialize(json);
		EmployeeAssembler.updateEmployee(employeeDTO);
	}

}
