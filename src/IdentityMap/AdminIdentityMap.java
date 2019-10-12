package IdentityMap;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class AdminIdentityMap {
	
	private static AdminIdentityMap adminIdentityMap = null;
	private static Map<Integer, Admin> map = new HashMap<>();

	private AdminIdentityMap() {}
	
	public static AdminIdentityMap getInstance(){
		if(adminIdentityMap == null) {
			adminIdentityMap = new AdminIdentityMap();
		}
		return adminIdentityMap;
	}
	
	public void put(int id, Admin e) {
		map.put(id, e);
	}
	
	public Admin get(int id) {
		return map.get(id);
	}

}
