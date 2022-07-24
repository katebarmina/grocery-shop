<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
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
<c:set var="total" value="${0}"/>
<div class="container">
    <c:if test="${not empty products}">
        <table class="content-table">
            <thead>
            <tr>
                <th></th>
                <th>Product</th>
                <th>Price</th>
                <th>Quantity</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${products}">
                <tr>
                    <td><img src="${item.product.image}" alt="no photo was found" class="productImg"/></td>
                    <td><c:out value="${item.product.name}"/></td>
                    <td><c:out value="${item.product.price}"/></td>
                    <td><c:out value="${item.quantity}"/></td>
                    <c:set var = "total" value="${total + (item.product.price*item.quantity)}"/>
                    <td><a href="${pageContext.request.contextPath}/shoppingCart/remove?productId=${item.product.id}">Remove</a></td>
                </tr>

            </c:forEach>
            </tbody>

        </table>
        <h1>Total:<c:out value="${total}"/>$</h1>
        <div class="cart-button">
            <button><a href="/order">Checkout</a></button>
            <br>
            <button><a href="/listOfProducts">Continue shopping</a></button>
        </div>
    </c:if>
    <c:if test="${empty products}">
        <h1>Your cart is empty.</h1>
    </c:if>



</div>

<br>

</body>
</html>
