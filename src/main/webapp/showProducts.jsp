<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "models.Role"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>All products</title>
</head>
<body>
<ul>
    <a href="/shoppingCart">Cart</a>
    <br>
<c:forEach var="product" items="${products}">
    <img src="${product.image}" alt="no photo was found"/>
    <c:out value="${product.name}" />
    <c:out value="${product.price}" />
    <c:out value="${product.brand}" />
    <a href="${pageContext.request.contextPath}/shoppingCart/add?productId=${product.id}">Add to Cart</a>
    <c:if test="${sessionScope.user.getRole().equals(Role.ADMIN)}">
        <a href="${pageContext.request.contextPath}/deleteProduct?productId=${product.id}">Delete</a>
        <a href="${pageContext.request.contextPath}/editProduct?productId=${product.id}">Edit</a>
    </c:if>
    <br>
    <br>
</c:forEach>
    <c:if test="${sessionScope.user.getRole().equals(Role.ADMIN)}">
        <a href=${pageContext.request.contextPath}/addProduct>Add new product</a>
    </c:if>
</ul>
</body>
</html>
