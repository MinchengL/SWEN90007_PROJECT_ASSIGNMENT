<%@page import="java.awt.Window"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="models.*" import="data_mapper.*" import="java.util.ArrayList" import="service_layer.*"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Attendance Management</title>
</head>

<body>
<nav>
<a href="departmentManagement.jsp">Department Management</a> |
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

<table>
  <tr>
    <th>Employee ID</th>
    <th>First Name</th> 
    <th>Last Name</th>
    <th>Department</th>
    <th>Phone Number</th>
    <th>Date of Birth</th>
    <th>Email</th>
  </tr>
</table>

</br>
<button onclick="window.location='addEmployee.jsp'">Add New Employee</button>

</body>
</html>