
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<div class="container">
    <form action="/order" method="post">
        <div class="inputBox">
            <span>CARD NUMBER</span>
            <input type="text" maxlength="16" name="cardNumber">
        </div>
        <div class="inputBox">
            <span>CARD HOLDER</span>
            <input type="text" name="cardHolderName">
        </div>
        <div class="flexbox">
            <div class="inputBox">
                <span>EXPIRATION MONTH</span>
                <select name="month">
                    <option value="01">01</option>
                    <option value="02">02</option>
                    <option value="03">03</option>
                    <option value="04">04</option>
                    <option value="05">05</option>
                    <option value="06">06</option>
                    <option value="07">07</option>
                    <option value="08">08</option>
                    <option value="09">09</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                </select>
            </div>
        </div>
        <div class="flexBox">
            <div class="inputBox">
                <span>EXPIRATION YY</span>
                <select name="year">
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                    <option value="2025">2025</option>
                    <option value="2026">2026</option>
                    <option value="2027">2027</option>
                </select>
            </div>
        </div>
        <div class="inputBox">
            <span>CVV</span>
            <input type="text" maxlength="4" name="cvv">
        </div>

        <input class="button" type="submit" value="PAY">
    </form>
</div>
</body>
</html>
