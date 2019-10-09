package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import data_mapper.LockManager;

/**
 * Servlet implementation class SearchAttendanceServlet
 */
@WebServlet("/SearchAttendanceServlet")
public class SearchAttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAttendanceServlet() {
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
			System.out.println("Acquire read lock when search attendance failed");
		}
		
		response.setContentType("text/html;charset=UTF-8");
		String str = request.getParameter("searchAttendance");
		request.getSession().setAttribute("searchAttendance", str);
//		ArrayList<AttendanceRecord> attendance = AttendanceService.searchDepartment(str);
//		response.sendRedirect("/attendanceManagement.jsp");
		response.sendRedirect("/SWEN90007_PROJECT_ASSIGNMENT/attendanceManagement.jsp");
		
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
