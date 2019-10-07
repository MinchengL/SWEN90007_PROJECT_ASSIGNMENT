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

ArrayList<Employee> dptlist = new ArrayList<>();
 %>
 
 <%= username %> + <%= type %>

<% if(usertype == 1) { %>
<div>
<form action="AttendanceClockServlet" method="post">
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
</table>

</body>
</html>