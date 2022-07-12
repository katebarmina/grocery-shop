
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log in</title>
</head>
<body>
<div class="form">
    <form action="<%=request.getContextPath()%>/loginServlet" method="post">
        <input type="email" required placeholder="Email" name="email"><br>
        <input type = "password" required placeholder = "password" name= "password"><br>
        <input class="button" type="submit" value="Log in">
    </form>
    <h3>Need an account?</h3><a href="registration.jsp"> Sign up</a>
</div>
</body>
</html>
