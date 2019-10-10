package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataMapper.LockManager;
import models.Department;
import serviceLayer.*;
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
		request.getSession().setAttribute("dptid", id);
		Department department = DepartmentService.getDepartment(id);
		request.getSession().setAttribute("department", department);
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/editDepartment.jsp");
//		response.sendRedirect("/editDepartment.jsp");
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
			System.out.println("Acquire write lock when editing department failed");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		Boolean valid = true;
		String name = request.getParameter("name");
		name = request.getParameter("name");
		if(name.length() > 25 || name == null) valid = false;
		
		String phonenumber = request.getParameter("phoneNumber");
		if(phonenumber.length() > 10) valid = false; 
		
		String location = request.getParameter("location");
		location = request.getParameter("location");
		if(location.length() > 25 || location == null) valid = false;
		
		String id = request.getSession().getAttribute("dptid").toString();
		Department department = DepartmentService.getDepartment(id);
		if(department == null) valid = false;
		
		if(valid == true) {
			DepartmentService.editDepartment(department, name, Integer.parseInt(phonenumber), location);
		}
		
		LockManager.getInstance().releaseWriteLock(session.getId());
		
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/departmentManagement.jsp");
//		response.sendRedirect("/departmentManagement.jsp");
	}

}
