package service_layer;

import models.*;

public class UserService {

	public User loginUser(String username, String password) {
		// TODO Auto-generated method stub
		Admin admin = Admin.loginbyAdmin(username, password);
		if(admin == null) {
			Employee empl = Employee.loginbyEmployee(username, password);
			return empl;
		}

		else return admin;
		
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
