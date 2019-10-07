<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="models.Department"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Edit Department</title>
</head>
<body>
<% 
Department department = (Department)session.getAttribute("department");
int id = department.getDepartmentID();
String name = department.getName();
int phoneNumber = department.getPhoneNumber();
String location = department.getLocation();
 %>
<div class="container">
	<form id="editDepartmentForm" name="/editDepartmentForm" action="EditDepartmentServlet" method="post">
		<table border="0">
		<tr>
			<td>Department ID:</td>
			<td><%= id %></td>
			</tr>
			<tr>
			<td>Department Name:</td>
			<td><input type="text" name="name" value="<%= name %>"></td>
			</tr>
			<tr>
			<td>Phone Number:</td>
			<td><input type="number" name="phoneNumber" value="<%= phoneNumber %>"></td>
			</tr>
			<tr>
			<td>Location:</td>
			<td><input type="text" name="location" value="<%= location %>"></td>
			</tr>
                </table>
            <br>
                <input type="submit" value="ConFirm">
	</form>
</div>
</body>
</html>