package unitofwork;

import java.util.ArrayList;

import models.Employee;
import models.Employee;
import dataMapper.*;

public class unitofworkEmployee {
	
	private static ThreadLocal<unitofworkEmployee> current = new ThreadLocal<>();
	
	private ArrayList<Employee> newEmployees = new ArrayList<Employee>();
	private ArrayList<Employee> dirtyEmployees = new ArrayList<Employee>();
	private ArrayList<Employee> deletedEmployees = new ArrayList<Employee>();
	
	public static void setCurrent(unitofworkEmployee uow) {
		current.set(uow);
	}
	
	public static void newCurrent() {
		setCurrent(new unitofworkEmployee());
	}
	
	public static unitofworkEmployee getCurrent() {
		return (unitofworkEmployee) current.get();
	}
	
	public void registerNew(Employee employee) {
		assert (employee.getUserID()<=0):"id is null";
		assert (!dirtyEmployees.contains(employee)):"this object is dirty";
		assert (!deletedEmployees.contains(employee)):"this object has been deleted";
		assert (!newEmployees.contains(employee)):"this object is new";
		newEmployees.add(employee);
	}
	
	public void registerDirty(Employee employee) {
		assert (employee.getUserID()<=0):"id is null";
		assert (!deletedEmployees.contains(employee)):"this object has been deleted";
		if(!dirtyEmployees.contains(employee) && !newEmployees.contains(employee)) {
			dirtyEmployees.add(employee);
		}
	}
	
	public void registerDeleted(Employee employee) {
		assert (employee.getUserID()<=0):"id is null";
		if(newEmployees.remove(employee)) return;
		dirtyEmployees.remove(employee);
		if(!deletedEmployees.contains(employee)) {
			deletedEmployees.add(employee);
		}
	}
	
	public void commit() {
		for(Employee employee: newEmployees) {
			EmployeeDataMapper.insert(employee);
		}
	
		for(Employee employee: dirtyEmployees) {
			EmployeeDataMapper.update(employee);
		}
		
		for(Employee employee: deletedEmployees) {
			EmployeeDataMapper.delete(employee);
		}
		
	}
}
