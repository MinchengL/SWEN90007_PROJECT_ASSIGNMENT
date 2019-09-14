<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Add Department</title>
</head>
<body>
<div class="container">
	<form id="addDepartmentForm" name="/addDepartmentForm" action="AddDepartmentServlet" method="post">
		<table border="0">
			<tr>
			<td>Department Name:</td>
			<td><input type="text" name="name"></td>
			</tr>
			<tr>
			<td>Phone Number:</td>
			<td><input type="number" name="phoneNumber"></td>
			</tr>
			<td>Location:</td>
			<td><input type="text" name="location"></td>
			</tr>
                </table>
            <br>
                <input type="submit" value="ConFirm">
	</form>
    </div>
</body>
</html>