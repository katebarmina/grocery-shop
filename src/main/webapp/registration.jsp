<%--
  Created by IntelliJ IDEA.
  User: Kate
  Date: 29.06.2022
  Time: 14:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<div class="container">
    <form action="<%=request.getContextPath()%>/registrationServlet" method="post">
        <input type="email" required placeholder="Enter Email " name="email"><br>
        <input type="password" required placeholder="Password" name="password"><br>
        <input class="button" type="submit" value="Sign up">
    </form>
</div>
</body>
</html>
