<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product</title>
</head>
<body>
<form method="post" action="/editProduct">
    <input type="hidden" name="productId" value="${productId}">
   PRODUCT NAME:<input type="text"  name="productName"><br>
    PRICE:<input type = "number" min="0" step=".01" name= "price"><br>
    BRAND:<input type="text" name="brand"><br>
    CATEGORY ID:<input type="number" name="categoryId"><br>
    <input class="button" type="submit" value="ACCEPT CHANGES">
</form>

</body>
</html>
