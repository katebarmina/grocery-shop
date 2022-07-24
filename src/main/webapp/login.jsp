<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="authorization-container">
        <div class="form">

    <form action="<%=request.getContextPath()%>/login" method="post">

        <div class="inputBox">
            <span>Email</span>
            <input type="email" required placeholder="Email" name="email"><br>
        </div>
        <div class="inputBox">
            <span>Password</span>
            <input type = "password" required placeholder = "Password" name= "password"><br>
        </div>
        <input class="button" type="submit" value="LOGIN">
    </form>

    </div>
        <c:if test="${not empty incorrectLog}">
            <h4 class="incorrect-log">Incorrect email or password!</h4>
        </c:if>
        <h3>Need an account?<a href="registration.jsp"> Sign up</a></h3>
</div>
</body>
</html>
