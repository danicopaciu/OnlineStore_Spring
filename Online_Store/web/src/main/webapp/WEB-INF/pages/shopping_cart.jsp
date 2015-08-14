<%@ page import="persistence.model.Product" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daniel.copaciu
  Date: 8/7/2015
  Time: 9:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
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

    <p><a href="javascript:logout()">Logout</a></p>
</c:if>

<p><a href=" <c:url value="/user/ProductList" />">Products List</a></p>
<c:if test="${cart.size() > 0}">
<table border="1">
    <tr>
        <th>Name</th>
        <th>Price</th>
        <th>Quantity</th>
        <th>Cost</th>
    </tr>
    <% List<Product> cart = (List<Product>) session.getAttribute("cart");
        double totalCost = 0;
        if (cart != null) {
            for (Product p : cart) {
                double partialCost = p.getPrice() * p.getQuantity();
                totalCost += partialCost;
    %>
    <tr>
        <td><% out.print(p.getName()); %></td>
        <td><% out.print(p.getPrice()); %></td>
        <td><% out.print(p.getQuantity()); %></td>
        <td><% out.print(partialCost); %></td>
        <td><a href="/user/DeleteFromCart?productId=<% out.print(p.getId());%>">Remove</a></td>
    </tr>
    <% } %>
    <% } %>
</table>
<p>Total cost is: <% out.print(totalCost);%></p>

<form action="/user/SubmitOrder" name="submitOrderForm" method="post">
    <input type="submit" name="submitOrder" value="Submit Order"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</c:if>
</body>
</html>
