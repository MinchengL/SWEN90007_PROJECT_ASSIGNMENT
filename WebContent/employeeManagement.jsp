<%@page import="java.awt.Window"%>
<%@page import="servlet.AppSession"%>
<%@page import="java.util.regex.Pattern" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="models.*" import="dataMapper.*" import="java.util.ArrayList" import="serviceLayer.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Employee Management</title>
<style>
th, td {
  padding-left: 20px;
}
</style>
</head>

<body>
<nav>
<a href="departmentManagement.jsp">Department Management</a> |
<a href="employeeManagement.jsp">Employee Management</a> |
<a href="attendanceManagement.jsp">Attendance Management</a> |
<a href="LogoutServlet">Logout</a>
</nav>

<% 
int usertype = (int)session.getAttribute("usertype");
int user_id = (int)session.getAttribute("user_id");
User user;
if(usertype==1){
	user=Admin.getAdminById(user_id);
}
else{
	user=Employee.getEmployeeById(user_id+"");
}
String username = user.getUserName();
String type = usertype == 1 ? "admin" : "employee";

ArrayList<Employee> emplist = new ArrayList<>();
Object str = session.getAttribute("searchEmployee");
String regex = "\\d*";
if(str != null && Pattern.matches(regex, str.toString())) {emplist = EmployeeService.searchEmployee(str.toString());}
else {emplist = EmployeeService.getAllEmployee();
str = "Enter valid Employee ID";}
int len = emplist == null ? 0: emplist.size();
 %>
 
 <%= username %> + <%= type %>

<form id="searchform" name="/searchForm" action="SearchEmployeeServlet" method="post">
     Search for Employee:
     <input type="text" name="searchEmployee" value="<%=str%>">
     <input type="submit" value="Search">
</form>
<form id="clearform" name="/clearForm" action="ClearEmployeeServlet" method="post">
	<input type="submit" value="Clear">
</form>

<table>
  <tr>
    <th>Employee ID</th>
    <th>Name</th> 
    <th>Department</th>
    <th>Phone Number</th>
    <th>Date of Birth</th>
    <th>Email</th>
  </tr>
  
<% int i = 0;
for(i = 0; i < len; i++){ 
%>
		<tr>
			 <td align = "center"><%= emplist.get(i).getUserID() %></td>
			 <td align = "center"><%= emplist.get(i).getUserName() %></td>
			 <td align = "center"><%= emplist.get(i).getDepartment().getName() %></td>
			 <td align = "center"><%= emplist.get(i).getPhoneNumber() %></td>
			 <td align = "center"><%= emplist.get(i).getBirthday() %></td>
			 <td align = "center"><%= emplist.get(i).getEmail() %></td>
			 <% if(AppSession.isAuthenticated() && (AppSession.hasRole(AppSession.ADMIN_ROLE) || (AppSession.hasRole(AppSession.EMPLOYEE_ROLE)&&emplist.get(i).getUserID()==user_id))){ %>
			 <td>
			 <a href="EditEmployeeServlet?id=<%=emplist.get(i).getUserID()%>"  onclick="window.location='editEmployee.jsp'">Edit</a>
			 </td>
			 <% } %>
			 <% if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.ADMIN_ROLE)) { %>
			 <td>
			 <a  href="DeleteEmployeeServlet?id=<%=emplist.get(i).getUserID()%>">Delete</a>
			 </td>
			 <% } %>
		 </tr>
<% }
 %>
  
</table>

</br>
<% if (AppSession.isAuthenticated() && AppSession.hasRole(AppSession.ADMIN_ROLE)) {%>
<button onclick="window.location='addEmployee.jsp'">Add New Employee</button>
<% } %>

</body>
</html>