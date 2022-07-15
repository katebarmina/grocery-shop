<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 13.07.2022
  Time: 1:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Provide further information</title>
</head>
<body>
<div class="form">
    <form action="/orderServlet" method="post">
        <input type="text" required placeholder="Address" name="address" >
        <input type="text" maxlength="16" name="cardNumber">
        <input type="text" name="cardHolderName">
        <input type="number" name="mmYY">
        <input type="number" name="cvv">
        <input class="button" type="submit" value="Pay">
    </form>
</div>
</body>
</html>
