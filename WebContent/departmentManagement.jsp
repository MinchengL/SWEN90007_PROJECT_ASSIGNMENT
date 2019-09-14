<%@ page language="java" contentType="text/html; charset=ISO-8859-1" 
import="models.*" import="data_mapper.*" import="java.util.ArrayList" import="service_layer.*"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Department Management</title>
</head>

<body>
<nav>
<a href="departmentManagement.jsp">Department Management</a> |
<a href="departmentManagement.jsp">Attendance Management</a> |
<a href="index.jsp">Logout</a>
</nav>

<div class="container">
<% 
User user = (User)session.getAttribute("user");
String username = user.getUserName();
int usertype = (int)session.getAttribute("usertype");
ArrayList<Department> dptlist = feature_a.getAllDepartment();
int len = dptlist.size();
 %>
 
 <%= username %> + <%= usertype %>
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
			 <td><input type="submit" value="Edit" style="color:#000000"></td>
			 <td><input type="submit" value="Delete" style="color:#000000"></td>
		 </tr>
<% }
 %>

</table>
<button onclick="window.location='addDepartment.jsp'">Add New Department</button>

</div>
</body>

</html>