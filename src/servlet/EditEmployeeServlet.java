package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		Employee employee = EmployeeService.getEmployee(id);
		request.getSession().setAttribute("employee", employee);
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/editEmployee.jsp");
//		response.sendRedirect("/editEmployee.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String department = request.getParameter("department");
		String phonenumber = request.getParameter("phoneNumber");
		String birthday = request.getParameter("birthday");
		String email = request.getParameter("email");
		String id = request.getSession().getAttribute("empid").toString();
		Employee employee = EmployeeService.getEmployee(id);
		EmployeeService.editEmployee(employee, firstName, lastName, department, Integer.parseInt(phonenumber), birthday, email);
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/employeeManagement.jsp");
//		response.sendRedirect("/employeeManagement.jsp");
	}

}
