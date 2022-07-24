<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 15.07.2022
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Store management</title>

</head>
<body>
<div class="header">
    <a href="index.jsp">HOME</a>
    <a href="listOfProducts">MANAGE PRODUCT</a>
    <a href="/listOfOrders">MANAGE ORDERS</a>
    <a href="/showAllUsers">MANAGE USERS</a>
    <a href="/logout">LOG OUT</a>
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
