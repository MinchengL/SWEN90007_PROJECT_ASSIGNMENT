package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data_mapper.LockManager;
import models.Employee;
import service_layer.EmployeeService;

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
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String department = request.getParameter("department");
		String phonenumber = request.getParameter("phoneNumber");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String id = request.getSession().getAttribute("empid").toString();
		Employee employee = EmployeeService.getEmployeeById(id);
		if(employee != null && firstName != null && lastName != null && department != null && phonenumber != null && birthday != null && email != null) {
			EmployeeService.editEmployee(employee, firstName, lastName, department, Integer.parseInt(phonenumber), birthday, email);
		}
		
		LockManager.getInstance().releaseWriteLock(session.getId());
		
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/employeeManagement.jsp");
//		response.sendRedirect("/employeeManagement.jsp");
	}

}
