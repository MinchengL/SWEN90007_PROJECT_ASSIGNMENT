package IdentityMap;

import java.util.HashMap;
import java.util.Map;
import models.*;

public class AdminIdentityMap {
	
	private static AdminIdentityMap adminIdentityMap = null;
	private static Map<String, Admin> map = new HashMap<>();

	private AdminIdentityMap() {}
	
	public static AdminIdentityMap getInstance(){
		if(adminIdentityMap == null) {
			adminIdentityMap = new AdminIdentityMap();
		}
		return adminIdentityMap;
	}
	
	public void put(String username, Admin e) {
		map.put(username, e);
	}
	
	public Admin get(String username) {
		return map.get(username);
	}
	

}
