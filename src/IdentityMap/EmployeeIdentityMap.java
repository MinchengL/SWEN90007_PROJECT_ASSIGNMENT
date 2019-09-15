package IdentityMap;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class EmployeeIdentityMap {
	
	private static EmployeeIdentityMap employeeIdentityMap = null;
	private static Map<String, Employee> map = new HashMap<>();

	private EmployeeIdentityMap() {}
	
	public static EmployeeIdentityMap getInstance(){
		if(employeeIdentityMap == null) {
			employeeIdentityMap = new EmployeeIdentityMap();
		}
		return employeeIdentityMap;
	}
	
	public void put(String username, Employee e) {
		map.put(username, e);
	}
	
	public Employee get(String username) {
		return map.get(username);
	}
	

}
