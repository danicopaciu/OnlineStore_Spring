<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Daniel.Copaciu
  Date: 8/5/2015
  Time: 10:06 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Page</title>
</head>
<body>
    <h1>Welcome on user page!</h1>
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>

    <form action="${logoutUrl}" method="POST" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>

    <script>
        function formSubmit() {
            document.getElementById("logoutForm").submit();
        }
    </script>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p> Welcome: ${pageContext.request.userPrincipal.name} </p>

        <p><a href="javascript:formSubmit()">Logout</a></p>
    </c:if>
</body>
</html>
