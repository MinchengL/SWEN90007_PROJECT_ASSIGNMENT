package servlet;

import serviceLayer.EmployeeService;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataMapper.LockManager;

/**
 * Servlet implementation class AddEmployeeServlet
 */
@WebServlet("/AddEmployeeServlet")
public class AddEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			LockManager.getInstance().acquireWriteLock(session.getId());
		} catch (InterruptedException e) {
			System.out.println("Acquire write lock when adding employee failed");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		Boolean valid = true;
		
		String username = request.getParameter("userName");
		if(username.length() > 25 || username == null) valid = false;
		
		String password = request.getParameter("password");
		if(password.length() > 25 || password == null) valid = false;
		
		String firstName = request.getParameter("firstName");
		if(firstName.length() > 25 || firstName == null) valid = false;
		
		String lastName = request.getParameter("lastName");
		if(lastName.length() > 25 || lastName == null) valid = false;
		
		String department = request.getParameter("department");
		if(department.length() > 25 || department == null) valid = false;
		
		int phoneNumber = 0;
		String tmpPhoneNumber = request.getParameter("phoneNumber");
		if(tmpPhoneNumber.length() <= 10) {
			phoneNumber = Integer.parseInt(tmpPhoneNumber);
		}
		else valid = false;
		
		String birthday = request.getParameter("birthday");
		if(birthday.length() > 25 || birthday == null) valid = false;
		
		String email = request.getParameter("email");
		if(email.length() > 25 || email == null) valid = false;
		
		if(valid == true) {
			EmployeeService.addEmployee(username, password, firstName, lastName, department, phoneNumber, birthday, email);
		}
		
		LockManager.getInstance().releaseWriteLock(session.getId());
		
//		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/employeeManagement.jsp");
		response.sendRedirect("/employeeManagement.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
