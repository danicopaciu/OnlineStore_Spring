<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daniel.copaciu
  Date: 8/13/2015
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>User List</h1>
<c:url value="/j_spring_security_logout" var="logoutUrl"/>

<form action="${logoutUrl}" method="POST" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>

<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>
<br><br>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p> Welcome: ${pageContext.request.userPrincipal.name} </p>

    <p><a href="javascript:formSubmit()">Logout</a></p>
</c:if>
<table border="1">
    <tr>
        <th>Username</th>
        <th>Password</th>
        <th colspan="2">Roles</th>
        <th colspan="3">Operations</th>
    </tr>
    <c:forEach var="user" items="${userList}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <c:choose>
                <c:when test="${user.userRoles.size() == 1}">
                    <c:forEach items="${user.userRoles}" var="userRole">
                        <c:choose>
                            <c:when test="${userRole.role == 'ROLE_USER'}">
                                <td>USER</td>
                                <td></td>
                                <td><a href="/admin/changeAuthority?username=${user.username}&type=makeAdmin">Make
                                    admin</a></td>
                                <td><a href="/admin/removeUser?username=${user.username}">Remove Account</a></td>
                            </c:when>
                            <c:otherwise>
                                <td>ADMIN</td>
                                <td></td>
                                <td><a href="/admin/changeAuthority?username=${user.username}&type=removeAdmin">Remove
                                    admin</a></td>
                                <td><a href="/admin/removeUser?username=${user.username}">Remove Account</a></td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${user.userRoles}" var="userRole">
                        <c:choose>
                            <c:when test="${userRole.role == 'ROLE_USER'}">
                                <td>USER</td>
                            </c:when>
                            <c:otherwise>
                                <td>ADMIN</td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <td><a href="/admin/changeAuthority?username=${user.username}&type=removeAdmin">Remove admin</a>
                    </td>
                    <td><a href="/admin/removeUser?username=${user.username}">Remove Account</a></td>
                </c:otherwise>
            </c:choose>
            <td><a href="/admin/ViewOrdersForUser?username=${user.username}">View Orders</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
