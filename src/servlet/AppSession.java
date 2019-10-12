package servlet;

import models.*;
import org.apache.shiro.SecurityUtils;

public class AppSession {
	public static final String USER_ATTRIBUTE_NAME = "user";
	public static final String ADMIN_ROLE = "admin";
	public static final String EMPLOYEE_ROLE = "employee";
	
	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}
	
	public static boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}
	
	public static void init(User user) {
		SecurityUtils.getSubject().getSession().setAttribute(USER_ATTRIBUTE_NAME, user);
	}
	
	public static User getUser() {
		return (User) SecurityUtils.getSubject().getSession().getAttribute(USER_ATTRIBUTE_NAME);
	}
	
}
