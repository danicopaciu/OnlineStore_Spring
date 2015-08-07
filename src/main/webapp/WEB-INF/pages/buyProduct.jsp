<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Daniel.Copaciu
  Date: 8/6/2015
  Time: 5:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>Fill in the quantity!</h1>
    <form name="buyForm" action="/user/buyProduct" method="post">
        <table>
            <tr>
                <td>Name:</td>
                <td>${product.name}</td>
            </tr>
            <tr>
                <td>Price:</td>
                <td>${product.price}</td>
            </tr>

            <tr>
                <td>Quantity:</td>
                <td><input type="number" name="product_quantity"/></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="submit_quantity" value="Add to chart"></td>
            </tr>
            <input type="hidden" name="productId" value="${product.id}">
        </table>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form><br>
    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>
</body>
</html>
