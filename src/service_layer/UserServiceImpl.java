package service_layer;

import models.*;

public class UserServiceImpl implements IUserService {

	@Override
	public void loginUser(User user) {
		// TODO Auto-generated method stub
		if(user == null) { // check username & pwd failed
		}
		else { // check un & pwd success
			
		}
		
	}

	@Override
	public User loginUser(String username, String password) {
		// TODO Auto-generated method stub
		Admin admin = SystemService.loginbyAdmin(username, password);
		if(admin == null) {
			Employee empl = SystemService.loginbyEmployee(username, password);
			return empl;
		}

		else return admin;
		
	}
	
	@Override
	public int getUsertype(String username, String password) {
		if(SystemService.loginbyAdmin(username, password) != null) {
			return 1;
		}
		else if (SystemService.loginbyEmployee(username, password) != null) {
			return 2;
		}
		return 0;
	}
}
