package unitofwork;

import java.util.ArrayList;

import models.Admin;
import models.Department;
import dataMapper.*;

public class unitofworkDepartment {
	
	private static ThreadLocal<unitofworkDepartment> current = new ThreadLocal<>();
	
	private ArrayList<Department> newDepartments = new ArrayList<Department>();
	private ArrayList<Department> dirtyDepartments = new ArrayList<Department>();
	private ArrayList<Department> deletedDepartments = new ArrayList<Department>();
	
	public static void setCurrent(unitofworkDepartment uow) {
		current.set(uow);
	}
	
	public static void newCurrent() {
		setCurrent(new unitofworkDepartment());
	}
	
	public static unitofworkDepartment getCurrent() {
		return (unitofworkDepartment) current.get();
	}
	
	public void registerNew(Department department) {
		assert (department.getDepartmentID()<=0):"id is null";
		assert (!dirtyDepartments.contains(department)):"this object is dirty";
		assert (!deletedDepartments.contains(department)):"this object has been deleted";
		assert (!newDepartments.contains(department)):"this object is new";
		newDepartments.add(department);
	}
	
	public void registerDirty(Department department) {
		assert (department.getDepartmentID()<=0):"id is null";
		assert (!deletedDepartments.contains(department)):"this object has been deleted";
		if(!dirtyDepartments.contains(department) && !newDepartments.contains(department)) {
			dirtyDepartments.add(department);
		}
	}
	
	public void registerDeleted(Department department) {
		assert (department.getDepartmentID()<=0):"id is null";
		if(newDepartments.remove(department)) return;
		dirtyDepartments.remove(department);
		if(!deletedDepartments.contains(department)) {
			deletedDepartments.add(department);
		}
	}
	
	public void commit() {
		for(Department department: newDepartments) {
			DepartmentDataMapper.insert(department);
		}
	
		for(Department department: dirtyDepartments) {
			DepartmentDataMapper.update(department);
		}
		
		for(Department department: deletedDepartments) {
			DepartmentDataMapper.delete(department);
		}
		
	}
}
