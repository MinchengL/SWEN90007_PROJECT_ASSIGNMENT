package IdentityMap;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class EmployeeIdentityMap {
	
	private static EmployeeIdentityMap employeeIdentityMap = null;
	private static Map<Integer, Employee> map = new HashMap<>();

	private EmployeeIdentityMap() {}
	
	public static EmployeeIdentityMap getInstance(){
		if(employeeIdentityMap == null) {
			employeeIdentityMap = new EmployeeIdentityMap();
		}
		return employeeIdentityMap;
	}
	
	public void put(int id, Employee e) {
		map.put(id, e);
	}
	
	public Employee get(int id) {
		return map.get(id);
	}
	

}
