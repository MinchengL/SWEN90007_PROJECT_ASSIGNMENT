package DTO;

import java.util.ArrayList;

import data_mapper.AdminDataMapper;
import models.Admin;
import models.Department;

public class AdminAssembler {

	public static AdminDTO createAdminDTO(Admin admin) {
		// TODO Auto-generated method stub
		AdminDTO dto = new AdminDTO();
		dto.setUserID(admin.getUserID());
		dto.setUserName(admin.getUserName());
		dto.setPassWord(admin.getPassWord());
		dto.setFirstName(admin.getFirstName());
		dto.setLastName(admin.getLastName());
		ArrayList<DepartmentDTO> departmentDTOs = new ArrayList<>();
		for(int i=0; i<admin.getDepartment().size(); i++) {
			DepartmentDTO departmentDTO = DepartmentAssembler.createDepartmentDTO(admin.getDepartment().get(i));
			departmentDTOs.add(departmentDTO);
		}
		dto.setDepartment(departmentDTOs);
		dto.setPhoneNumber(admin.getPhoneNumber());
		dto.setBirthday(admin.getBirthday());
		dto.setEmail(admin.getEmail());
		return dto;
	}

	public static void updateAdmin(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		
		admin.setId(adminDTO.getUserID());
		admin.setUserName(adminDTO.getUserName());
		admin.setPassWord(adminDTO.getPassWord());
		admin.setFirstName(adminDTO.getFirstName());
		admin.setLastName(adminDTO.getLastName());
		ArrayList<Department> departments = new ArrayList<>();
		for(int i=0; i<adminDTO.getDepartment().size(); i++) {
			Department department = DepartmentAssembler.createDepartment(adminDTO.getDepartment().get(i));
			
		}
		admin.setDepartment(departments);
		admin.setPhoneNumber(adminDTO.getPhoneNumber());
		admin.setBirthday(adminDTO.getBirthday());
		admin.setEmail(adminDTO.getEmail());
		
		AdminDataMapper.update(admin);
	}

	public static Admin createAdmin(AdminDTO adminDTO) {
		// TODO Auto-generated method stub
		Admin admin = new Admin();
		
		admin.setId(adminDTO.getUserID());
		admin.setUserName(adminDTO.getUserName());
		admin.setPassWord(adminDTO.getPassWord());
		admin.setFirstName(adminDTO.getFirstName());
		admin.setLastName(adminDTO.getLastName());
		ArrayList<Department> departments = new ArrayList<>();
		for(int i=0; i<adminDTO.getDepartment().size(); i++) {
			Department department = DepartmentAssembler.createDepartment(adminDTO.getDepartment().get(i));
			
		}
		admin.setDepartment(departments);
		admin.setPhoneNumber(adminDTO.getPhoneNumber());
		admin.setBirthday(adminDTO.getBirthday());
		admin.setEmail(adminDTO.getEmail());
		return admin;
	}

}
