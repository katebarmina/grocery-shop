<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Manage Products</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="header">
    <a href="index.jsp">HOME</a>
    <a href="listOfProducts">Manage products</a>
    <a href="/listOfOrders">Manage orders</a>
    <a href="/showAllUsers">Manage users</a>
    <a href="/logout">Log out</a>
</div>

<div class="container">
    <h1>All products</h1>
    <table class="content-table">
        <thead>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Image</th>
            <th>Brand</th>
            <th>Price</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="product" items="${products}">
            <tr>
                <td><c:out value="${product.id}" /></td>
                <td><c:out value="${product.name}" /></td>
                <td><img src="${product.image}" alt="no photo was found" class="productImg"/></td>
                <td><c:out value="${product.brand}" /></td>
                <td><c:out value="${product.price}" />$</td>
                <td><a href="${pageContext.request.contextPath}/deleteProduct?productId=${product.id}">Delete</a></td>
                <td><a href="${pageContext.request.contextPath}/editProduct?productId=${product.id}">Edit</a></td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
        <button class="add-product-button"><a href=${pageContext.request.contextPath}/addProduct>Add new product</a></button>
</div>
</body>
</html>
