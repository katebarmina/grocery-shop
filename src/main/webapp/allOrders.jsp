<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Manage orders</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="header">
    <a href="index.jsp">HOME</a>
    <a href="listOfProducts">MANAGE PRODUCT</a>
    <a href="/listOfOrders">MANAGE ORDERS</a>
    <a href="/showAllUsers">MANAGE USERS</a>
    <a href="/logout">LOG OUT</a>
</div>
<div class="container">
    <h1>ALL ORDERS</h1>
    <table class="content-table">
        <thead>
        <tr>
            <th>ORDER ID</th>
            <th>USER ID</th>
            <th>STATUS</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody><c:forEach var="order" items="${orders}">
            <tr>
                <td><c:out value="${order.orderId}"/></td>
                <td><c:out value="${order.userId}"/></td>
                <td><c:out value="${order.status}"/></td>
                <td><a href="${pageContext.request.contextPath}/deleteOrder?orderId=${order.orderId}">Delete</a></td>
                <td><form method="post" action="/updateStatus?orderId=${order.orderId}">
                    <select id="orderStatus" name="orderStatus">
                        <option value="STATUS" disabled selected>STATUS</option>
                        <option value="PAYMENT_RECEIVED">PAYMENT_RECEIVED</option>
                        <option value="PAYMENT_FAILED">PAYMENT_FAILED</option>
                        <option value="PROCESSING">PROCESSING</option>
                        <option value="SHIPPED">SHIPPED</option>
                        <option value="DELIVERED">DELIVERED</option>
                        <option value="CANCELED">CANCELED</option>
                    </select>
                    <button type="submit">SAVE CHANGES</button>
                </form>
                </td>

            </tr>

        </c:forEach></tbody>

    </table>
</div>

</body>
</html>
