<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit product</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="authorization-container">
    <div class="form">
        <form method="post" action="/editProduct">
            <input type="hidden" name="productId" value="${productId}">
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
                <input type="text" name="brand">
            </div>
            <div class="inputBox">
                <span>CATEGORY ID:</span>
                <input type="number" name="categoryId">
            </div>
            <input class="button" type="submit" value="ACCEPT CHANGES">
        </form>
    </div>

</div>


</body>
</html>
