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
		Admin admin = AuthenticationService.loginbyAdmin(username, password);
		if(admin == null) {
			Employee empl = AuthenticationService.loginbyEmployee(username, password);
			return empl;
		}

		else return admin;
		
	}
	
	@Override
	public int getUsertype(String username, String password) {
		if(AuthenticationService.loginbyAdmin(username, password) != null) {
			return 1;
		}
		else if (AuthenticationService.loginbyEmployee(username, password) != null) {
			return 2;
		}
		return 0;
	}
}
