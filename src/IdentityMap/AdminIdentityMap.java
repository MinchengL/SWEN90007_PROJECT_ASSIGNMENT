package IdentityMap;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class AdminIdentityMap {
	
	private static AdminIdentityMap adminIdentityMap = null;
	private static Map<Long, Admin> map = new HashMap<>();

	private AdminIdentityMap() {}
	
	public static AdminIdentityMap getInstance(){
		if(adminIdentityMap == null) {
			adminIdentityMap = new AdminIdentityMap();
		}
		return adminIdentityMap;
	}
	
	public void put(long id, Admin e) {
		map.put(id, e);
	}
	
	public Admin get(long id) {
		return map.get(id);
	}
	

}
