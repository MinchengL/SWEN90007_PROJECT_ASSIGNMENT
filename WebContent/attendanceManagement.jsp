<%@page import="servlet.AppSession"%>
<%@page import="java.awt.Window"%>
<%@page import="java.util.regex.Pattern" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" 
import="models.*" import="dataMapper.*" import="java.util.ArrayList" import="serviceLayer.*" 
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

ArrayList<AttendanceRecord> atdlist = new ArrayList<>();
Object str = session.getAttribute("searchAttendance");
String regex = "\\d*";
if(str != null && Pattern.matches(regex, str.toString())) {atdlist = AttendanceService.searchByEmployee(str.toString());}
else {atdlist = AttendanceService.getAllAttendance();
str = "Enter valid employee ID";}
if (usertype == 2) {atdlist = AttendanceService.searchByEmployee(user.getUserID()+"");}
int len = atdlist == null ? 0 : atdlist.size();
 %>
 
<%= username %> + <%= type %>

<% if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.ADMIN_ROLE)) { %>
<form id="searchform" name="/searchForm" action="SearchAttendanceServlet" method="post">
     Search by Employee:
     <input type="text" name="searchAttendance" value="<%=str%>">
     <input type="submit" value="Search">
</form>
<form id="clearform" name="/clearForm" action="ClearAttendanceServlet" method="post">
	<input type="submit" value="Clear">
</form>
<% } %>

<% if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.EMPLOYEE_ROLE)) { %>
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
    <th>Employee Name</th>
    <th>Clock Type</th> 
    <th>Clock Time</th>
  </tr>
  
<% int i = 0;
for(i = 0; i < len; i++){ 
%>
		<tr>
			 <td align = "center"><%= atdlist.get(i).getEmployee().getUserID() %></td>
			 <td align = "center"><%= atdlist.get(i).getEmployee().getUserName() %></td>
			 <td align = "center"><%= atdlist.get(i).getOperationType() %></td>
			 <td align = "center"><%= atdlist.get(i).getOperationTime() %></td>
		 </tr>
<% }
 %>
  
</table>

</body>
</html>