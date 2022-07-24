
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Registration</title>
</head>
<body>

<div class="authorization-container">
    <h1>REGISTRATION</h1>
    <form action="<%=request.getContextPath()%>/registration" method="post">
       <div class="inputBox">
           <span>Email:</span>
           <input type="email" required placeholder="Enter Email " name="email">
       </div>
        <div class="inputBox">
            <span>Password:</span>
            <input type="password" required placeholder="Password" name="password">
        </div>

        <input class="button" type="submit" value="Sign up">
    </form>
</div>
</body>
</html>
