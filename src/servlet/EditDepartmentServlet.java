package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Department;
import service_layer.*;
import unitofwork.unitofworkDepartment;

/**
 * Servlet implementation class EditDepartment
 */
@WebServlet("/EditDepartmentServlet")
public class EditDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditDepartmentServlet() {
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
		request.getSession().setAttribute("id", id);
		Department department = SystemService.getDepartment(id);
		request.getSession().setAttribute("department", department);
		
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/editDepartment.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=UTF-8");
		String name = request.getParameter("name");
		String phonenumber = request.getParameter("phoneNumber");
		String location = request.getParameter("location");
		String id = request.getSession().getAttribute("id").toString();
		Department department = SystemService.getDepartment(id);
		SystemService.editDepartment(department, name, Integer.parseInt(phonenumber), location);
		
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/departmentManagement.jsp");
	}

}
