<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Daniel.Copaciu
  Date: 8/6/2015
  Time: 11:21 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Update information for product ${productId}</h1>
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
    <p><a href="javascript:formSubmit()">Logout</a></p>
</c:if>

<c:if test="${not empty msg}">
    <p>${msg}</p>
</c:if>
<form action="/admin/UpdateProduct" name="update_product" method="post">
    <table>
        <tr>
            <td>ID:</td>
            <td><input type="text" name="product_id" value="${product.id}" readonly="readonly"></td>
        </tr>
        <tr>
            <td>Name:</td>
            <td><input type="text" name="product_name" value="${product.name}"></td>
        </tr>
        <tr>
            <td>Price:</td>
            <td><input type="number" step="0.01" name="product_price" value="${product.price}"/></td>
        </tr>
        <tr>
            <td>Quantity:</td>
            <td><input type="number" name="product_quantity" value="${product.quantity}"/></td>
        </tr>
        <tr>
            <td></td>
            <td align="right"><input type="submit" name="update_product" value="Update Product!"/></td>
        </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
</form>
</body>
</html>
