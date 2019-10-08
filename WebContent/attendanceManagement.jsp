<%@page import="java.awt.Window"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="models.*" import="data_mapper.*" import="java.util.ArrayList" import="service_layer.*" 
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Attendance Management</title>
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
User user = (User)session.getAttribute("user");
String username = user.getUserName();
int usertype = (int)session.getAttribute("usertype");
String type = usertype == 1 ? "admin" : "employee";

ArrayList<AttendanceRecord> atdlist = new ArrayList<>();
Object str = session.getAttribute("searchAttendance");
if(str != null) {atdlist = AttendanceService.searchByEmployee(str.toString());}
else {atdlist = AttendanceService.getAllAttendance();}
if (usertype == 0) {atdlist = AttendanceService.searchByEmployee(user.getUserID()+"");}
int len = atdlist == null ? 0: atdlist.size();
 %>
 
<%= username %> + <%= type %>

<% if(usertype == 1) { %>
<form id="searchform" name="/searchForm" action="SearchAttendanceServlet" method="post">
     Search by Employee:
     <input type="text" name="searchAttendance" placeholder="Employee ID">
     <input type="submit" value="Search">
</form>
<form id="clearform" name="/clearForm" action="ClearAttendanceServlet" method="post">
	<input type="submit" value="Clear">
</form>
<% } %>

<% if(usertype == 0) { %>
<div>
<form action="AttendanceClockServlet?id=<%=user.getUserID() %>" method="post">
    <button type="submit" name="button" value="clockOn">Clock-on</button>
    <button type="submit" name="button" value="clockOff">Clock-off</button>
</form>
</div>
<% } %>
 
<table>
  <tr>
    <th>Employee ID</th>
    <th>Clock Type</th> 
    <th>Clock Time</th>
  </tr>
  
<% int i = 0;
for(i = 0; i < len; i++){ 
%>
		<tr>
			 <td align = "center"><%= atdlist.get(i).getUser().getUserName() %></td>
			 <td align = "center"><%= atdlist.get(i).getOperationType() %></td>
			 <td align = "center"><%= atdlist.get(i).getOperationTime() %></td>
		 </tr>
<% }
 %>
  
</table>

</body>
</html>