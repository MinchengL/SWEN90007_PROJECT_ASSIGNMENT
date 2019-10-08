package servlet;

import data_mapper.*;
import models.*;
import unitofwork.unitofworkDepartment;
import database.*;
import service_layer.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import IdentityMap.DepartmentIdentityMap;
import unitofwork.*;

/**
 * Servlet implementation class DatabaseInitializationServlet
 */
@WebServlet("/DatabaseInitializationServlet")
public class DatabaseInitializationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DatabaseInitializationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() throws ServletException{
    		
    		Database_Initailisation.initailize_dataset();
    		unitofworkAdmin.newCurrent();
    		unitofworkDepartment.newCurrent();
    		unitofworkEmployee.newCurrent();
    		unitofworkAttendanceRecord.newCurrent();
    		//System.out.println(feature_a.loginbyAdmin("jack", "1234").getLastName());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		printWriter.write("<h3> helloworld <h3>");
		response.sendRedirect("/index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
