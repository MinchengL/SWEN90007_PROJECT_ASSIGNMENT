package IdentityMap;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class DepartmentIdentityMap {
	
	private static DepartmentIdentityMap departmentIdentityMap = null;
	private static Map<Long, Department> map = new HashMap<>();

	private DepartmentIdentityMap() {}
	
	public static DepartmentIdentityMap getInstance(){
		if(departmentIdentityMap == null) {
			departmentIdentityMap = new DepartmentIdentityMap();
		}
		return departmentIdentityMap;
	}
	
	public void put(long id, Department e) {
		map.put(id, e);
	}
	
	public Department get(long id) {
		return map.get(id);
	}
	

}
