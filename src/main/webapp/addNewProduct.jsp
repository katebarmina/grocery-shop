<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 16.07.2022
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New product</title>
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
<div class="authorization-container">
    <form action="<%=request.getContextPath()%>/addProduct" method="post">
        <div class="inputBox">
            <span>PRODUCT NAME:</span>
            <input type="text"  name="productName">
        </div>
        <div class="inputBox">
            <span>PRICE:</span>
            <input type = "number" min="0" step=".01" name= "price">
        </div>
        <div class="inputBox">
            <span>BRAND:</span>
            <input type="text" name="brand"><br>
        </div>
        <div class="inputBox">
            <span>CATEGORY ID:</span>
            <input type="number" name="categoryId"><br>
        </div>
        <input class="button" type="submit" value="ADD NEW PRODUCT">
    </form>
</div>


</body>
</html>
