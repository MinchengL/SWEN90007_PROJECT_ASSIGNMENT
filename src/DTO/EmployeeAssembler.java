package DTO;

import java.util.ArrayList;

import dataMapper.AdminDataMapper;
import dataMapper.EmployeeDataMapper;
import models.Admin;
import models.Department;
import models.Employee;

public class EmployeeAssembler {

	public static EmployeeDTO createEmployeeDTO(Employee employee) {
		// TODO Auto-generated method stub
		EmployeeDTO dto = new EmployeeDTO();
		dto.setUserID(employee.getUserID());
		dto.setUserName(employee.getUserName());
		dto.setPassWord(employee.getPassWord());
		dto.setFirstName(employee.getFirstName());
		dto.setLastName(employee.getLastName());
		DepartmentDTO departmentDTO = DepartmentAssembler.createDepartmentDTO(employee.getDepartment());
		dto.setDepartment(departmentDTO);
		dto.setPhoneNumber(employee.getPhoneNumber());
		dto.setBirthday(employee.getBirthday());
		dto.setEmail(employee.getEmail());
		return dto;
	}
	
	public static void updateEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		Employee employee = new Employee();
		
		employee.setUserID(employeeDTO.getUserID());
		employee.setUserName(employeeDTO.getUserName());
		employee.setPassWord(employeeDTO.getPassWord());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		Department department = DepartmentAssembler.createDepartment(employeeDTO.getDepartment());
		employee.setDepartment(department);
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setBirthday(employeeDTO.getBirthday());
		employee.setEmail(employeeDTO.getEmail());
		
		EmployeeDataMapper.update(employee);
	}
	
	public static Employee createEmployee(EmployeeDTO employeeDTO) {
		Employee employee = new Employee();
		
		employee.setUserID(employeeDTO.getUserID());
		employee.setUserName(employeeDTO.getUserName());
		employee.setPassWord(employeeDTO.getPassWord());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		Department department = DepartmentAssembler.createDepartment(employeeDTO.getDepartment());
		employee.setDepartment(department);
		employee.setPhoneNumber(employeeDTO.getPhoneNumber());
		employee.setBirthday(employeeDTO.getBirthday());
		employee.setEmail(employeeDTO.getEmail());
		
		return employee;
	}

}
