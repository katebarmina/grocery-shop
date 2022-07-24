<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Role" %>

<!DOCTYPE html>
<html>
<head>

    <title>Home</title>
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
        <a href="/registration">REGISTER</a>
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

<div class="mainPic-container">
    <img src="images/mainPage/background.jpg" alt="cart with products">
    <form action="listOfProducts">
        <h3>Better Products At The Right Price</h3>

        <button type="submit" value="Shop now">Shop Now</button>
    </form>
</div>

</body>
</html>