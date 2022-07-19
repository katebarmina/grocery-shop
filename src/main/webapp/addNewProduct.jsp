<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 16.07.2022
  Time: 0:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New product</title>
</head>
<body>
<form action="<%=request.getContextPath()%>/addProduct" method="post">
    Product name:<input type="text"  name="productName"><br>
    Price:<input type = "number" min="0" step=".01" name= "price"><br>
    Brand:<input type="text" name="brand"><br>
    Category id:<input type="number" name="categoryId"><br>
    <input class="button" type="submit" value="Add new product">
</form>

</body>
</html>
