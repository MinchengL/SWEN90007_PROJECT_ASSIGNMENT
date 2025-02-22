package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dataMapper.LockManager;

/**
 * Servlet implementation class ClearEmployeeServlet
 */
@WebServlet("/ClearEmployeeServlet")
public class ClearEmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClearEmployeeServlet() {
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
			System.out.println("Acquire read lock when clear employee failed");
		}
		
		session.setAttribute("searchEmployee", null);
//		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/employeeManagement.jsp");
		response.sendRedirect("/employeeManagement.jsp");
		
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
