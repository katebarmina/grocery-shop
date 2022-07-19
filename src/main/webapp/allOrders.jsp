<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 19.07.2022
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage orders</title>
</head>
<body>
<a href="listOfProducts">List Of Products</a>
<a href="/login">Login</a>
<a href="/shoppingCart">Cart</a>
<br>

<c:forEach var="order" items="${orders}">
    <c:out value="${order.orderId}"/>
    <c:out value="${order.userId}"/>
    <c:out value="${order.status}"/>
    <a href="${pageContext.request.contextPath}/deleteOrder?orderId=${order.orderId}">Delete</a>
    <br>
    Change status:
    <form method="post" action="/updateStatus?orderId=${order.orderId}">
            <select id="orderStatus" name="orderStatus">
                <option value="PAYMENT_RECEIVED">PAYMENT_RECEIVED</option>
                <option value="PAYMENT_FAILED">PAYMENT_FAILED</option>
                <option value="PROCESSING">PROCESSING</option>
                <option value="SHIPPED">SHIPPED</option>
                <option value="DELIVERED">DELIVERED</option>
                <option value="CANCELED">CANCELED</option>
            </select>
        <button type="submit">Save changes</button>
    </form>
</c:forEach>
</body>
</html>
