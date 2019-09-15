package servlet;

import service_layer.*;
import models.*;
import data_mapper.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String username = null;
		username = request.getParameter("username");
		String password = null;
		password = request.getParameter("password");
		IUserService service = new UserServiceImpl();
		User user = service.loginUser(username, password);
		
		if(user == null) {
			//response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/loginFailed.jsp");
			response.sendRedirect("/loginFailed.jsp");
		}
		else {
			int userType = service.getUsertype(username, password);
			Logger logger = Logger.getAnonymousLogger();
			logger.info("usertype is "+userType);
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("usertype", userType);
			//response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/departmentManagement.jsp");
			response.sendRedirect("/departmentManagement.jsp");
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
