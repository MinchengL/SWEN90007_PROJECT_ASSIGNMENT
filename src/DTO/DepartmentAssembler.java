package DTO;

import java.util.ArrayList;

import dataMapper.DepartmentDataMapper;
import models.Admin;
import models.Department;
import models.Employee;

public class DepartmentAssembler {
	
	public static DepartmentDTO createDepartmentDTO(Department department) {
		
		DepartmentDTO dto = new DepartmentDTO();
		dto.setDepartmentID(department.getDepartmentID());
		dto.setName(department.getName());
		dto.setPhoneNumber(department.getPhoneNumber());
		dto.setLocation(department.getLocation());
		ArrayList<EmployeeDTO> employeeDTOs = new ArrayList<>();
		for(int i=0; i<department.getEmployees().size(); i++) {
			EmployeeDTO employeeDTO = EmployeeAssembler.createEmployeeDTO(department.getEmployees().get(i));
			employeeDTOs.add(employeeDTO);
		}
		dto.setEmployees(employeeDTOs);
		ArrayList<AdminDTO> adminDTOs = new ArrayList<>();
		for(int i=0; i<department.getAdmins().size(); i++) {
			AdminDTO adminDTO = AdminAssembler.createAdminDTO(department.getAdmins().get(i));
			adminDTOs.add(adminDTO);
		}
		dto.setAdmins(adminDTOs);
		return dto;
	}
	
	public static void updateDepartment(DepartmentDTO departmentDTO) {
		
		Department department = new Department();
		department.setDepartmentID(departmentDTO.getDepartmentID());
		department.setName(departmentDTO.getName());
		department.setLocation(departmentDTO.getLocation());
		department.setPhoneNumber(departmentDTO.getPhoneNumber());
		for(int i=0; i<departmentDTO.getAdmins().size();i++) {
			Admin admin = AdminAssembler.createAdmin(departmentDTO.getAdmins().get(i));
			department.setAdmins(admin);
		}
		
		DepartmentDataMapper.update(department);
		
	}

	public static Department createDepartment(DepartmentDTO departmentDTO) {
		// TODO Auto-generated method stub
		Department department = new Department();
		department.setDepartmentID(departmentDTO.getDepartmentID());
		department.setName(departmentDTO.getName());
		department.setLocation(departmentDTO.getLocation());
		department.setPhoneNumber(departmentDTO.getPhoneNumber());
		for(int i=0; i<departmentDTO.getAdmins().size();i++) {
			Admin admin = AdminAssembler.createAdmin(departmentDTO.getAdmins().get(i));
			department.setAdmins(admin);
		}
		return department;
	}

}
