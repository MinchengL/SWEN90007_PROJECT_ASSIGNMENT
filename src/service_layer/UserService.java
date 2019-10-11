package service_layer;

import models.*;

public class UserService {

	public User loginUser(String username, String password, String role) {
		// TODO Auto-generated method stub
		if(role.equals("admin")) {
			Admin admin = Admin.loginbyAdmin(username, password);
			return admin;
		}
		Employee empl = Employee.loginbyEmployee(username, password);
		return empl;
		
	}
	
	public int getUsertype(String username, String password) {
		if(Admin.loginbyAdmin(username, password) != null) {
			return 1;
		}
		else if (Employee.loginbyEmployee(username, password) != null) {
			return 2;
		}
		return 0;
	}
}
