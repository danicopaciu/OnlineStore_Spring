<%--
  Created by IntelliJ IDEA.
  User: daniel.copaciu
  Date: 8/12/2015
  Time: 10:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Register</h1>

<form name="registerForm" action="/register" method="POST">
    <table align="center">
        <tr>
            <td>Username:</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>Confirm Password:</td>
            <td><input type="password" name="matchingPassword"></td>
        </tr>
        <tr>
            <td></td>
            <td align="right"><input type="submit" name="submit" value="Register"></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
</html>
