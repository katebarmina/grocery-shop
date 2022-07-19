<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="models.Role" %>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Home" %>
</h1>
<br/>

<c:if test="${sessionScope.user.getRole().equals(Role.ADMIN)}">
    <a href="/manageShop">Manage shop</a>
</c:if>
<a href="listOfProducts">List Of Products</a>
<a href="/login">Login</a>
<a href="/shoppingCart">Cart</a>

</body>
</html>