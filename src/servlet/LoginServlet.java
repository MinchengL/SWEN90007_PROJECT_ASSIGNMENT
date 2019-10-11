package servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import models.User;
import serviceLayer.UserService;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet(
		"/LoginServlet"
		)
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		Boolean valid = true;
		String username = null;
		username = request.getParameter("username");
		//String regex = "^[0-9]*$";
		//valid =  username.matches(regex);
		String password = null;
		password = request.getParameter("password");
		String role = "admin";
		role = request.getParameter("role");
		
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();
		
		UserService service = new UserService();
		User user = null;
		if(valid == true) {
		try {
			user = service.loginUser(username, password,role);
			if(user != null) {
				currentUser.login(token);
			}

		} catch (UnknownAccountException | IncorrectCredentialsException e) {
			response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/loginFailed.jsp");
//			response.sendRedirect("/loginFailed.jsp");
		} finally {
			if(user == null) {
				response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/loginFailed.jsp");
//				response.sendRedirect("/loginFailed.jsp");
			}
			else {
				AppSession.init(user);
				int userType = role.equals("admin") ? 1 : 2;
//				Logger logger = Logger.getAnonymousLogger();
//				logger.info("usertype is "+userType);
				HttpSession session = request.getSession();
				session.setAttribute("user_id", Integer.parseInt(username));
				session.setAttribute("usertype", userType);
				response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/departmentManagement.jsp");
//				response.sendRedirect("/departmentManagement.jsp");
			}
		}
		}
		else {
			response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/loginFailed.jsp");
//			response.sendRedirect("/loginFailed.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
