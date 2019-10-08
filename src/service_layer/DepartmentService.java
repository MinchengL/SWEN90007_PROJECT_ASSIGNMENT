package service_layer;

import java.util.ArrayList;

import data_mapper.DepartmentDataMapper;
import models.Department;
import unitofwork.unitofworkDepartment;

public class DepartmentService {
	
	public static ArrayList<Department> getAllDepartment(){
		return Department.getAllDepartmentList();
	}
	
	public static Department getDepartment(String id) {
		return Department.getDepartmentById(id);
	}
	
	public static void addDepartment(String name, int phoneNumber, String location) {
		Department department = new Department(name, phoneNumber, location);
		if(unitofworkDepartment.getCurrent()==null) {
			unitofworkDepartment.newCurrent();
		}
		unitofworkDepartment.getCurrent().commit();
		
	}
	
	public static void editDepartment(Department department, String name, int phoneNumber, String location) {
		department.setLocation(location);
		department.setName(name);
		department.setPhoneNumber(phoneNumber);
		if(unitofworkDepartment.getCurrent()==null)
		{
			unitofworkDepartment.newCurrent();
		}
		unitofworkDepartment.getCurrent().commit();
	}
	
	public static void deleteDepartment(String id) {
		Department department = Department.getDepartmentById(id);
		if(unitofworkDepartment.getCurrent()==null) {
			unitofworkDepartment.newCurrent();
		}
		unitofworkDepartment.getCurrent().registerDeleted(department);
		unitofworkDepartment.getCurrent().commit();
	}
	
	public static ArrayList<Department> searchDepartment(String str){
		ArrayList<Department> departments = new ArrayList<>();
		Department department = Department.getDepartmentById(str);
		if(department!=null) {
			departments.add(department);
		}
		department = Department.getDepartmentByName(str);
		if(department!=null) {
			departments.add(department);
		}
		return departments;
	}
}
