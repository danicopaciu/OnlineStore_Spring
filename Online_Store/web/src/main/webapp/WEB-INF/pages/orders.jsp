<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: daniel.copaciu
  Date: 8/10/2015
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Order List</h1>
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
  <p> Welcome: ${pageContext.request.userPrincipal.name} </p>
  <p> <a href="javascript:formSubmit()">Logout</a> </p>
</c:if>

<table>
  <c:forEach var="item" items="${orderList}">
    <tr>
      <td>
    <table border="1">
      <tr>
        <td>Order ID</td>
        <td>${item.id}</td>
      </tr>
      <tr>
        <td>Cost</td>
        <td>${item.cost}</td>
      </tr>
      <tr>
        <td>Date</td>
        <td>${item.date}</td>
      </tr>
      <tr>
        <td>Products</td>
        <td>
          <table border="1">
            <tr>
              <th>Item ID</th>
              <th>Product ID</th>
              <th>Name</th>
              <th>Price</th>
              <th>Quantity</th>
              <th>Cost</th>
            </tr>
            <c:forEach var="orderItem" items="${item.itemList}">
              <tr>
                <td>${orderItem.id}</td>
                <td>${orderItem.product.id}</td>
                <td>${orderItem.product.name}</td>
                <td>${orderItem.product.price}</td>
                <td>${orderItem.quantity}</td>
                <td>${orderItem.subTotal}</td>
                <td><a href="/admin/deleteOrderItem?orderId=${item.id}&orderItemId=${orderItem.id}">Remove</a> </td>
              </tr>
            </c:forEach>
          </table>
        </td>
      </tr>
      <tr>
        <td><a href="">Update</a> </td>
        <td><a href="/admin/deleteOrder?orderId=${item.id}">Remove</a> </td>
      </tr>
    </table>
    <br>
    </td>
</tr>
  </c:forEach>
  </table>

</body>
</html>
