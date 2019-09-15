package service_layer;

import models.User;

public interface IUserService {
	void loginUser(User user);
	User loginUser(String username, String password);
	int getUsertype(String username, String password);
}
