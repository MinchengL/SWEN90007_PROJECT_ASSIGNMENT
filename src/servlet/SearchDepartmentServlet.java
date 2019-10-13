package servlet;

import serviceLayer.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataMapper.LockManager;
import models.Department;

/**
 * Servlet implementation class SearchDepartmentServlet
 */
@WebServlet("/SearchDepartmentServlet")
public class SearchDepartmentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchDepartmentServlet() {
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
			LockManager.getInstance().acquireReadLock(session.getId());
		} catch (InterruptedException e) {
			System.out.println("Acquire read lock when search department failed");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		String str = request.getParameter("searchDepartment");
		request.getSession().setAttribute("searchDepartment", str);
//		ArrayList<Department> departments = DepartmentService.searchDepartment(str);
		response.sendRedirect("/departmentManagement.jsp");
//		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/departmentManagement.jsp");

		LockManager.getInstance().releaseReadLock(session.getId());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
