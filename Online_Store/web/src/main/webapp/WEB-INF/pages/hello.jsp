<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
    <h1>Welcome</h1>
    <p><a href="/register">Register</a></p>
    <form name="loginForm" action=/j_spring_security_check method="POST">
        <table align="center">
            <tr>
                <td>User:</td>
                <td><input type="text" name="username"></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"></td>
            </tr>
            <tr>
                <td></td>
                <td align="right"><input type="submit" name="submit" value="Log In!"></td>
            </tr>
        </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <c:if test="${not empty error}">
        <p>${error}</p>
    </c:if>

    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>
</body>
</html>