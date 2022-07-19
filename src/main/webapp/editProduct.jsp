<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit product</title>
</head>
<body>
<form method="post" action="/editProduct">
    <input type="hidden" name="productId" value="${productId}">
   Product name:<input type="text"  name="productName"><br>
    Price:<input type = "number" min="0" step=".01" name= "price"><br>
    Brand:<input type="text" name="brand"><br>
    Category id:<input type="number" name="categoryId"><br>
    <input class="button" type="submit" value="Accept changes">
</form>

</body>
</html>
