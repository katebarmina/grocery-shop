<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <c:out value="${product.name}" />
    <c:out value="${product.price}" />
    <c:out value="${product.brand}" />
    <a href="${pageContext.request.contextPath}/shoppingCart/add?productId=${product.id}">Add</a>
    <br>
    <br>
</c:forEach>
</ul>
</body>
</html>
