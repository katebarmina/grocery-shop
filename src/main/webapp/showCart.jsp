<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My cart</title>
    <a href="listOfProducts">List Of Products</a>
    <br>
</head>
<body>
<c:forEach var="item" items="${products}">
    <c:out value="${item.product}"/>
    <br>
    Quantity:<c:out value="${item.quantity}"/>
    <a href="${pageContext.request.contextPath}/shoppingCart/remove?productId=${item.product.id}">Remove</a>
    <br>
</c:forEach>
<br>
<a href="/listOfProducts">Continue shopping</a>
</body>
</html>
