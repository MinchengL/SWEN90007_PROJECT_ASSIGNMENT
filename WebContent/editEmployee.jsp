<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="models.Employee" import="models.Department"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Edit Employee</title>
</head>
<body>
<% 
Employee employee = (Employee)session.getAttribute("employee");
int id = employee.getUserID();
String firstName = employee.getFirstName();
String lastName = employee.getLastName();
String department = employee.getDepartment().getName();
int phoneNumber = employee.getPhoneNumber();
String birthday = employee.getBirthday();
String email = employee.getEmail();
 %>

<div class="container">
	<form id="editEmployeeForm" name="/editEmployeeForm" action="EditEmployeeServlet" method="post">
		<table border="0">
			<tr>
			<td>Employee ID:</td>
			<td><%= id %></td>
			</tr>
			<tr>
			<td>First Name:</td>
			<td><input type="text" name="firstName" value="<%= firstName %>"></td>
			</tr>
			<tr>
			<td>Last Name:</td>
			<td><input type="text" name="lastName" value="<%= lastName %>"></td>
			</tr>
			<tr>
			<td>Department:</td>
			<td><input type="text" name="department" value="<%= department %>"></td>
			</tr>
			<tr>
			<td>Phone Number:</td>
			<td><input type="number" name="phoneNumber" value="<%= phoneNumber %>"></td>
			</tr>
			<tr>
			<td>Date of Birth:</td>
			<td><input type="date" name="birthday" value="<%= birthday %>"></td>
			</tr>
			<tr>
			<td>Email:</td>
			<td><input type="text" name="email" value="<%= email %>"></td>
			</tr>
                </table>
            <br>
                <input type="submit" value="ConFirm">
	</form>
</div>
 
</body>
</html>