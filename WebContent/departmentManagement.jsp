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
<title>Human Resource System-Department Management</title>
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

<div class="container">
<% 
int usertype = (int)session.getAttribute("usertype");
int user_id = (int)session.getAttribute("user_id");
User user;
if(usertype == 1){
	user = Admin.getAdminById(user_id);
}
else{
	user = Employee.getEmployeeById(user_id+"");
}
String username = user.getUserName();
String type = usertype == 1 ? "admin" : "employee";

ArrayList<Department> dptlist = new ArrayList<>();
Object str = session.getAttribute("searchDepartment");
String regex = "\\d*";
if(str != null && Pattern.matches(regex, str.toString())) {dptlist = DepartmentService.searchDepartment(str.toString());}
else {dptlist = DepartmentService.getAllDepartment();
	str = "Enter valid department ID";}
int len = dptlist == null ? 0: dptlist.size();
 %>
 
 <%= username %> + <%= type %>
 
<form id="searchform" name="/searchForm" action="SearchDepartmentServlet" method="post">
     Search for Department:
     <input type="text" name="searchDepartment" value="<%=str%>">
     <input type="submit" value="Search">
</form>
<form id="clearform" name="/clearForm" action="ClearDepartmentServlet" method="post">
	<input type="submit" value="Clear">
</form>
            
<table id="departmentTable">

  <tr>
    <th>DepartmentID</th>
    <th>DepartmentName</th> 
    <th>PhoneNumber</th>
    <th>Location</th>
  </tr>
  
<% int i = 0;
for(i = 0; i < len; i++){ 
%>
		<tr>
			 <td align = "center"><%= dptlist.get(i).getDepartmentID() %></td>
			 <td align = "center"><%= dptlist.get(i).getName() %></td>
			 <td align = "center"><%= dptlist.get(i).getPhoneNumber() %></td>
			 <td align = "center"><%= dptlist.get(i).getLocation() %></td>
			 <% if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.ADMIN_ROLE)){ %>
			 <td>
			 <a href="EditDepartmentServlet?id=<%=dptlist.get(i).getDepartmentID()%>"  onclick="window.location='editDepartment.jsp'">Edit</a>
			 </td>
			 <td>
			 <a  href="DeleteDepartmentServlet?id=<%=dptlist.get(i).getDepartmentID()%>">Delete</a>
			 </td>
			 <% } %>
		 </tr>
<% }
 %>

</table>
</br>
<% if (AppSession.isAuthenticated() && AppSession.hasRole(AppSession.ADMIN_ROLE)) { %>
<button onclick="window.location='addDepartment.jsp'">Add New Department</button>
<% } %>

</div>
</body>

</html>