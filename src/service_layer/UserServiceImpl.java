package service_layer;

import models.User;

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
		return null; //return the user
	}
}
