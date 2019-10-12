package servlet;

import serviceLayer.*;

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
 * Servlet implementation class AttendanceClockServlet
 */
@WebServlet("/AttendanceClockServlet")
public class AttendanceClockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AttendanceClockServlet() {
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
			System.out.println("Acquire write lock when adding attendance failed");
		}
		
		String button = request.getParameter("button");
		String id = request.getParameter("id");
		Date date = new Date();
		if ("clockOn".equals(button)) { // clock on
            AttendanceService.insertClockOnRecord(id, date.toString());
        } 
		else if ("clockOff".equals(button)) { // clock off
            AttendanceService.insertClockOffRecord(id, date.toString());
        } 
		
		LockManager.getInstance().releaseWriteLock(session.getId());
		
		response.sendRedirect("/attendanceManagement.jsp");
//		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/attendanceManagement.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
