<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Daniel.Copaciu
  Date: 8/5/2015
  Time: 12:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
    <h1>Add a new product!</h1>
    <c:url value="/j_spring_security_logout" var="logoutUrl" />

    <form action="${logoutUrl}" method="POST" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>

    <script>
        function formSubmit(){
            document.getElementById("logoutForm").submit();
        }
    </script>

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <p> <a href="javascript:formSubmit()">Logout</a> </p>
    </c:if>

    <c:if test="${not empty msg}">
        <p>${msg}</p>
    </c:if>
    <form action="" name="add_product" method="post">
      <table>
        <tr>
          <td>Name: </td>
          <td><input type="text" name="product_name"></td>
        </tr>
        <tr>
          <td>Price: </td>
          <td><input type="number" step="0.01" name="product_price"/></td>
        </tr>
        <tr>
          <td>Quantity: </td>
          <td><input type="number" name="product_quantity"/></td>
        </tr>
        <tr>
          <td></td>
          <td align="right"><input type="submit" name="add_product" value="Add Product!"/></td>
        </tr>
      </table>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    </form>
    <table border="2">
        <thead>
            <td>ID</td>
            <td>Name</td>
            <td>Price</td>
            <td>Quantity</td>
        </thead>
        <c:forEach var="item" items="${productList}">
            <tr>
                <td>${item.id}</td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td><a href="" >Update</a></td>
                <td><a href="">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
