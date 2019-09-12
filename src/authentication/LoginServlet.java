package authentication;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SimpleServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String usernamed;
	private String passwordd;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig servletconfig) throws ServletException
    {
    		usernamed = servletconfig.getInitParameter("userNameI");
    		passwordd = servletconfig.getInitParameter("passWordI");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("UserName");
		String password = request.getParameter("passWord");
		response.setContentType("text/html");
		System.out.println("Hello from GET method");
		PrintWriter writer = response.getWriter();
		if (username.equals(usernamed) && password.equals(passwordd))
		{
			writer.println("<h3> Hello</h3>");
		}
		else
		{
			writer.println("<h3> Error</h3>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("passWord");
		response.setContentType("text/html");
		System.out.println("Hello from GET method");
		PrintWriter writer = response.getWriter();
		if (username.equals(usernamed) && password.equals(passwordd))
		{
			response.sendRedirect("success.jsp");
		}
		else
		{
			writer.println("<h3> Error</h3>");
		}
	}

}
