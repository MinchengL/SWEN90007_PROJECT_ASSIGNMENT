package servlet;

import data_mapper.*;
import models.*;
import unitofwork.unitofworkDepartment;
import database.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.StyledEditorKit.ForegroundAction;

import IdentityMap.DepartmentIdentityMap;

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
    		//Department department = new Department();
    		DepartmentIdentityMap identityMap = DepartmentIdentityMap.getInstance();
    		unitofworkDepartment.newCurrent();
    		Department department = identityMap.get(1);
    		if(department == null) {
    			department = DepartmentDataMapper.search("department_id", "1").get(0);
    			identityMap.put(1, department);
    		}
    		System.out.println(identityMap.get(1).getName());
    		department.setPhoneNumber(12345690);
    		unitofworkDepartment.getCurrent().commit();
    		
    		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
