package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataMapper.LockManager;
import models.Employee;
import serviceLayer.EmployeeService;

/**
 * Servlet implementation class EditEmployeeServlet
 */
@WebServlet("/EditEmployeeServlet")
public class EditEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployeeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("id");
		request.getSession().setAttribute("empid", id);
		Employee employee = EmployeeService.getEmployeeById(id);
		request.getSession().setAttribute("employee", employee);
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/editEmployee.jsp");
//		response.sendRedirect("/editEmployee.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		try {
			LockManager.getInstance().acquireWriteLock(session.getId());
		} catch (InterruptedException e) {
			System.out.println("Acquire write lock when editing employee failed");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		Boolean valid = true;
		
		String firstName = request.getParameter("firstName");
		if(firstName.length() > 25 || firstName == null) valid = false;
		
		String lastName = request.getParameter("lastName");
		if(lastName.length() > 25 || lastName == null) valid = false;
		
		String department = request.getParameter("department");
		if(department.length() > 25 || department == null) valid = false;
		
		String phonenumber = request.getParameter("phoneNumber");
		if(phonenumber.length() > 10 || phonenumber == null) valid = false;
		
		String birthday = request.getParameter("birthday");
		if(birthday.length() > 25 || birthday == null) valid = false;
		
		String email = request.getParameter("email");
		if(email.length() > 25 || email == null) valid = false;
		
		String id = request.getSession().getAttribute("empid").toString();
		Employee employee = EmployeeService.getEmployeeById(id);
		
		if(valid == true) {
			EmployeeService.editEmployee(employee, firstName, lastName, department, Integer.parseInt(phonenumber), birthday, email);
		}
		
		LockManager.getInstance().releaseWriteLock(session.getId());
		
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/employeeManagement.jsp");
//		response.sendRedirect("/employeeManagement.jsp");
	}

}
