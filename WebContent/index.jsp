<%@ page import="servlet.AppSession" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Human Resource System-Login</title>
</head>
<body>
	<div class="container">

	
        <h1 style="color:steelblue">Login</h1>
            <form id="loginform" name="/loginForm" action="LoginServlet" method="post">
                <table border="0">
                    <tr>
                        <td>Username:</td>
                        <td><input type="text" name="username" placeholder="Username"></td>
                    </tr>
                    <tr>
                        <td>Password:</td>
                        <td><input type="password" name="password" placeholder="Password" width="200px">
                        </td>
                    </tr>
                </table>
            <br>
                <input type="submit" value="Login">
            </form>

            
    </div>
</body>
</html>