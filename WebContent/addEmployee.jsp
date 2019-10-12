<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Human Resource System-Add Employee</title>
</head>
<body>
<div class="container">
	<form id="addEmployeeForm" name="/addEmployeeForm" action="AddEmployeeServlet" method="post">
		<table border="0">
			<tr>
			<td>User Name:</td>
			<td><input type="text" name="userName"></td>
			</tr>
			<tr>
			<td>Password:</td>
			<td><input type="text" name="password"></td>
			</tr>
			<tr>
			<td>First Name:</td>
			<td><input type="text" name="firstName"></td>
			</tr>
			<tr>
			<td>Last Name:</td>
			<td><input type="text" name="lastName"></td>
			</tr>
			<tr>
			<td>Department:</td>
			<td><input type="text" name="department"></td>
			</tr>
			<tr>
			<td>Phone Number:</td>
			<td><input type="number" name="phoneNumber"></td>
			</tr>
			<tr>
			<td>Date of Birth:</td>
			<td><input type="date" name="birthday"></td>
			</tr>
			<tr>
			<td>Email:</td>
			<td><input type="text" name="email"></td>
			</tr>
         </table>
            <br>
                <input type="submit" value="ConFirm">
	</form>
</div>
</body>
</html>