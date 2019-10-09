package RemoteFacade;

import DTO.DepartmentAssembler;
import DTO.DepartmentDTO;
import data_mapper.DepartmentDataMapper;
import models.Department;

public class DepartmentFacade {
	
	public DepartmentDTO getDepartment(int department_id) {
		Department department = DepartmentDataMapper.search("department_id", department_id+"");
		DepartmentDTO dto = DepartmentAssembler.createDepartmentDTO(department);
		return dto;
	}
	
	public void updateDepartment(DepartmentDTO departmentDTO) {
		DepartmentAssembler.updateDepartment(departmentDTO);
	}
	
	public String getDepartmentJson(int department_id) {
		Department department = DepartmentDataMapper.search("department_id", department_id+"");
		DepartmentDTO dto = DepartmentAssembler.createDepartmentDTO(department);
		return DepartmentDTO.serialize(dto);
	}
	
	public void updateDepartmentJson(String json) {
		DepartmentDTO departmentDTO = DepartmentDTO.deserialize(json);
		DepartmentAssembler.updateDepartment(departmentDTO);
	}

}
