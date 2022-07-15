<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 15.07.2022
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <a href="listOfProducts">List Of Products</a>
    <a href="loginServlet">Login</a>
    <a href="/shoppingCart">Cart</a>
</head>
<body>
<c:forEach var="user" items="${users}">
    <c:out value="${user.id}"/>
    <c:out value="${user.email}"/>
    <c:out value="${user.role}"/>
    <br>
    <a href="${pageContext.request.contextPath}/manageShop/deleteUser?userId=${user.id}">Delete</a>
    <br>
</c:forEach>
</body>
</html>
