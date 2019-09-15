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
<a href="departmentManagement.jsp">Attendance Management</a> |
<a href="index.jsp">Logout</a>
</nav>

<div class="container">
<% 
User user = (User)session.getAttribute("user");
String username = user.getUserName();
int usertype = (int)session.getAttribute("usertype");
String type = usertype == 1 ? "admin" : "employee";
ArrayList<Department> dptlist = SystemService.getAllDepartment();
int len = dptlist.size();
 %>
 
 <%= username %> + <%= type %>
 
<form id="searchform" name="/searchForm" action="LoginServlet" method="post">
                <table border="0">
                    <tr>
                        <td>Search for Department:</td>
                        <td><input type="text" name="searchDepartment" placeholder="Department ID or name"></td>
                    </tr>
                </table>
                <a href="SearchDepartmentServlet"  onclick="window.location='departmentDepartment.jsp'">Search</a>
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