<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My orders</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="header">
    <a href="index.jsp">HOME</a>
    <c:if test="${sessionScope.user.getRole().equals(Role.ADMIN)}">
        <a href="/manageShop">MANAGE SHOP</a>
    </c:if>
    <c:if test="${sessionScope.user!=null}">
        <a href="/logout">LOG OUT</a>
    </c:if>
    <c:if test="${sessionScope.user == null}">
        <a href="/login">LOG IN</a>
    </c:if>
    <c:if test="${sessionScope.user != null && sessionScope.user.getRole().equals(Role.CLIENT)}">
        <a href="/showUserOrders">MY ORDERS</a>
    </c:if>
    <c:if test="${sessionScope.user == null || sessionScope.user.getRole().equals(Role.CLIENT)}">
        <div class="icon">
            <a href="/shoppingCart"><img  src="images/mainPage/cart-79-512.png"></a>
        </div>
    </c:if>
</div>

<div class="container">
    <h1>MY ORDERS</h1>
    <table class="content-table">
        <thead>
        <tr>
            <th>ORDER ID</th>
            <th>STATUS</th>
        </tr>
        </thead>
        <tbody><c:forEach var="order" items="${userOrders}">
            <tr>
                <td><c:out value="${order.orderId}"/></td>
                <td><c:out value="${order.status}"/></td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    </div>
</body>
</html>
