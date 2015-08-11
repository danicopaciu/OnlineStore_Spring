<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Daniel.Copaciu
  Date: 8/5/2015
  Time: 9:39 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Page</title>
</head>
<body>
    <h1>Welcome on admin page!</h1>
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
        <tr>
            <td><a href="<c:url value='/admin/AddProduct' />">Add Product</a> </td>
        </tr>
        <tr>
            <td><a href="<c:url value='/admin/ViewOrders' />">View Orders</a> </td>
        </tr>
    </table>

    <table border="1">
        <tr>
            <th colspan="2">ID</th>
            <th colspan="2">Name</th>
            <th colspan="2">Price</th>
            <th colspan="2">Quantity</th>
        </tr>
        <c:forEach var="item" items="${productList}">
            <tr>
                <td colspan="2">${item.id}</td>
                <td colspan="2">${item.name}</td>
                <td colspan="2">${item.price}</td>
                <td colspan="2">${item.quantity}</td>
                <td colspan="2"><a href="/admin/UpdateProduct?productId=${item.id}" >Update</a></td>
                <td colspan="2"><a href="/admin/DeleteProduct?productId=${item.id}&page_name=${pageContext.request.servletPath}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
