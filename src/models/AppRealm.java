package models;

import java.util.HashSet;
import java.util.Set;

import javax.naming.AuthenticationException;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import servlet.AppSession;

public class AppRealm extends JdbcRealm {

	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		UsernamePasswordToken userPassToken = (UsernamePasswordToken)token;
		final String username = userPassToken.getUsername();
		final User user = User.getUser(username);
		if(user == null) {
			System.out.println("No account found for user " + username);
			return null;
		}
		return new SimpleAuthenticationInfo(user.getUserID(), user.getPassWord(), user.getUserName());
	}
	
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = new HashSet<>();
		if(principals.isEmpty()) {
			System.out.println("Given principals to authorize are empty");
			return null;
		}
		
		String username = (String) principals.getPrimaryPrincipal();
		final User user = User.getUser(username);
		
		if(user == null) {
			System.out.println("No account found for user " + username);
			return null;
		}
		
		if(user instanceof Admin) {
			roles.add(AppSession.ADMIN_ROLE);
		}
		else if(user instanceof Employee) {
			roles.add(AppSession.EMPLOYEE_ROLE);
		}
		
		return new SimpleAuthorizationInfo(roles);
		
	}
	
}
