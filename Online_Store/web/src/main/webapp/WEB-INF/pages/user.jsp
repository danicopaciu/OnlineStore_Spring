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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"/>
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

    function logout() {
        $.ajax({
            type: "GET",
            url: "/user/DeleteChart",
            complete: formSubmit()
        });
    }
</script>

<br><br>
<c:if test="${pageContext.request.userPrincipal.name != null}">
    <p> Welcome: ${pageContext.request.userPrincipal.name} </p>

    <p><a href="javascript:logout">Logout</a></p>
</c:if>

<table>
    <tr>
        <td>
            <c:choose>
                <c:when test="${cart != null}">
                    <a href="/user/ShoppingCart">Shopping Cart(${cart.size()})</a>
                </c:when>
                <c:otherwise>
                    <a href="">Shopping Cart(0)</a>
                </c:otherwise>
            </c:choose>
        </td>
        <td><a href="/user/ViewOrders">View your Orders</a></td>
        <td><a href="/user/RemoveAccount">Delete Account</a></td>
        <td><a href="/user/ChangePassword">Change Password</a></td>
    </tr>
</table>
<br><br>

<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
    </tr>
    <c:forEach var="item" items="${productList}">
        <tr>
            <td>${item.name}</td>
            <td>${item.price}</td>
            <c:choose>
                <c:when test="${item.quantity <= 0}">
                    <td>Unavailable</td>
                    <td><a href="" onclick="false">Buy!</a></td>
                </c:when>
                <c:otherwise>
                    <td>Available</td>
                    <td><a href="/user/buyProduct?productId=${item.id}">Buy!</a></td>
                </c:otherwise>
            </c:choose>

        </tr>
    </c:forEach>
</table>
</body>
</html>
