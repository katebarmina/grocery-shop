<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import = "com.barmina.models.Role"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>All products</title>
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
<div class="products-container">
    <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="column">
                <img src="${product.image}" alt="no photo was found" class="productImg"/>
                    <br>
                <c:out value="${product.name}" />
                    <br>
                    <c:out value="${product.brand}" />
                    <br>
                    <div class="price">
                        <c:out value="${product.price}" />$
                    </div>

                    <br>
                    <button><a href="${pageContext.request.contextPath}/shoppingCart/add?productId=${product.id}">Add to cart</a></button>
                <c:if test="${sessionScope.user.getRole().equals(Role.ADMIN)}">
                    <button><a href="${pageContext.request.contextPath}/deleteProduct?productId=${product.id}">Delete</a></button>
                    <br>
                    <button><a href="${pageContext.request.contextPath}/editProduct?productId=${product.id}">Edit</a></button>
                </c:if>
    </div>
            </c:forEach>

    </div>
    <c:if test="${sessionScope.user.getRole().equals(Role.ADMIN)}">
        <button><a href=${pageContext.request.contextPath}/addProduct>Add new product</a></button>
    </c:if>
</div>

</body>
</html>
