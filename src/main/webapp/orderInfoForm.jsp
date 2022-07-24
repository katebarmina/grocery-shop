
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Shipping Address</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="authorization-container">
    <h1>Shipping Address</h1>
    <div class="form">

        <form action="<%=request.getContextPath()%>/order" method="post">

            <div class="inputBox">
                <input type="text" required placeholder="First name" name="firstName"><br>
            </div>
            <div class="inputBox">
                <input type="text" required placeholder="Last name" name="last Name"><br>
            </div>
            <div class="inputBox">
                <input type = "text" required placeholder = "Address" name= "address"><br>
            </div>
            <div class="inputBox">
                <input type = "text" required placeholder = "Apartment,suit,etc." name= "apart"><br>
            </div>
            <div class="inputBox">
                <input type = "text" required placeholder = "City" name= "city"><br>
            </div>
            <div class="inputBox">
                <input type = "number" maxlength="10" required placeholder = "Phone" name= "phone"><br>
            </div>
            <button class="button" type="submit"><a href="/order.jsp">Continue To Shipping</a></button>
        </form>

    </div>
</div>
</body>
</html>
