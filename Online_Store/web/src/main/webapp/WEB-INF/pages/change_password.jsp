<%--
  Created by IntelliJ IDEA.
  User: daniel.copaciu
  Date: 8/12/2015
  Time: 4:21 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Register</h1>

<form name="changePasswordForm" action="/user/ChangePassword" method="POST">
    <table align="center">
        <tr>
            <td>New Password:</td>
            <td><input type="password" name="newPassword"></td>
        </tr>
        <tr>
            <td>Confirm New Password:</td>
            <td><input type="password" name="newMatchingPassword"></td>
        </tr>
        <tr>
            <td></td>
            <td align="right"><input type="submit" name="submit" value="Change Password"></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
</html>
