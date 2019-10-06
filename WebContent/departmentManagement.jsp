<%@page import="java.awt.Window"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="models.*" import="data_mapper.*" import="java.util.ArrayList" import="service_layer.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Department Management</title>
</head>

<body>
<nav>
<a href="departmentManagement.jsp">Department Management</a> |
<a href="attendanceManagement.jsp">Attendance Management</a> |
<a href="LogoutServlet">Logout</a>
</nav>

<div class="container">
<% 
User user = (User)session.getAttribute("user");
String username = user.getUserName();
int usertype = (int)session.getAttribute("usertype");
String type = usertype == 1 ? "admin" : "employee";

ArrayList<Department> dptlist = new ArrayList<>();
Object str = session.getAttribute("searchDepartment");
if(str != null) {dptlist = DepartmentService.searchDepartment(str.toString());}
else {dptlist = DepartmentService.getAllDepartment();}
int len = dptlist.size();
 %>
 
 <%= username %> + <%= type %>
 
<form id="searchform" name="/searchForm" action="SearchDepartmentServlet" method="post">
                <table border="0">
                    <tr>
                        <td>Search for Department:</td>
                        <td><input type="text" name="searchDepartment" placeholder="Department ID or name"></td>
                    </tr>
                </table>
                <input type="submit" value="Search">
</form>
<form id="clearform" name="/clearForm" action="ClearDepartmentServlet" method="post">
	<input type="submit" value="Clear">
</form>
            
<table>

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
			 <td>
			 <a href="EditDepartmentServlet?id=<%=dptlist.get(i).getDepartmentID()%>"  onclick="window.location='editDepartment.jsp'">Edit</a>
			 </td>
			 <td>
			 <a  href="DeleteDepartmentServlet?id=<%=dptlist.get(i).getDepartmentID()%>">Delete</a>
			 </td>
		 </tr>
<% }
 %>

</table>
</br>
<button onclick="window.location='addDepartment.jsp'">Add New Department</button>

</div>
</body>

</html>