package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

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
		String regex_phone = "^(?:\\+?61|0)[2-478](?:[ -]?[0-9]){8}$";
		if(phonenumber.length() > 10 || phonenumber == null || !Pattern.matches(regex_phone, phonenumber)) valid = false;
		
		String birthday = request.getParameter("birthday");
		String regex_date = "((((19|20)\\d{2})-(0?(1|[3-9])|1[012])-(0?[1-9]|[12]\\d|30))|(((19|20)\\d{2})-(0?[13578]|1[02])-31)|(((19|20)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-29))$";
		if(birthday.length() > 25 || birthday == null || !Pattern.matches(regex_date, birthday)) valid = false;
		
		String email = request.getParameter("email");
		String regex_email = "^([a-zA-Z]|[0-9])(\\w|-)+@[a-zA-Z0-9]+\\.([a-zA-Z]{2,4})$";
		if(email.length() > 25 || email == null || !Pattern.matches(regex_email, email)) valid = false;
		
		String id = request.getSession().getAttribute("empid").toString();
		Employee employee = EmployeeService.getEmployeeById(id);

		if(AppSession.isAuthenticated() && valid) {
			if(AppSession.hasRole(AppSession.ADMIN_ROLE)) {
				EmployeeService.editEmployee(employee, firstName, lastName, department, Integer.parseInt(phonenumber), birthday, email);
			}
		}
		else {
			PrintWriter out = response.getWriter();
			out.print("<script>alert('Illegal input or unauthenticated user'); window.location='employeeManagement.jsp' </script>");
			out.flush();
			out.close();
		}
		
		LockManager.getInstance().releaseWriteLock(session.getId());
		
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/employeeManagement.jsp");
//		response.sendRedirect("/employeeManagement.jsp");
	}

}
